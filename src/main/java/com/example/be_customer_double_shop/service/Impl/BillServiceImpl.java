package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.*;
import com.example.be_customer_double_shop.repository.BillRepository;
import com.example.be_customer_double_shop.service.*;
import com.example.be_customer_double_shop.util.Constant;
import com.example.be_customer_double_shop.util.DateUtil;
import com.example.be_customer_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private DetailBillService detailBillService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private DetailProductService detailProductService;

    @Autowired
    private BillHistoryService billHistoryService;

    @Autowired
    private CustomerVoucherService customerVoucherService;

    @Autowired
    private CustomerService customerService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartService cartService;

    @Override
    @Transactional
    public Object createBill(BillRequest billRequest, String username) {
        Customer customer = customerService.findUserbyUsername(username);
        String code = StringUtil.generateString(8);
        Boolean check = true;
        while (check) {
            code = StringUtil.generateString(8);
            check = billRepository.existsByCode(code);
        }
        Voucher voucher = null;
        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdVoucher())) {
            voucher = Voucher.builder().id(billRequest.getIdVoucher()).build();
        }
        // create bill
        Bill bill = billRepository.save(Bill.builder().code(code)
                .customer(customer)
                .totalAmount(billRequest.getTotalAmout())
                .voucher(voucher)
                .discountAmount(billRequest.getDiscoutAmout())
                .phone(billRequest.getPhone())
                .address(billRequest.getAddress())
                .note(billRequest.getNote())
                .status(Constant.BILL.STATUS.WAIT_CONFIRM)
                .payment(billRequest.getPayment())
                .moneyShip(billRequest.getMoneyShip())
                .createdTime(DateUtil.dateToString4(new Date()))
                .receiver(billRequest.getReceiver())
                .type(Constant.BILL.TYPE.DEVERILY).build());

        if (bill != null) {
            // create bill_history
            String description = "Đặt hàng";
            BillHistory billHistory = BillHistory.builder().bill(bill)
                    .status(Constant.BILL.STATUS.WAIT_CONFIRM)
                    .createdBy(username)
                    .createdTime(DateUtil.dateToString4(new Date()))
                    .description(description).build();
            billHistoryService.createBillHistory(billHistory);

            if (!StringUtil.stringIsNullOrEmty(billRequest.getIdVoucher())) {
                Voucher vou = voucherService.getOneById(billRequest.getIdVoucher());
                int voucherQuantity = vou.getQuantity() - 1;
                if (voucherQuantity == 0) {
                    vou.setStatus(Constant.IN_ACTIVE);
                }
                vou.setQuantity(voucherQuantity);
                voucherService.updateVoucher(vou, username);

                if (customer != null) {
                    CustomerVoucher customerVoucher = customerVoucherService.getByCustomerAndVoucher(customer.getId(), billRequest.getIdVoucher());
                    customerVoucher.setUsageDate(DateUtil.dateToString4(new Date()));
                    customerVoucherService.updateCustomerVoucher(customerVoucher);
                }
            }
            List<DetailProduct> detailProductList = detailProductService.getAllDetailProductByIdCart(billRequest.getListCart());

            for (int i = 0; i < detailProductList.size(); i++) {
                DetailProduct detailProduct = detailProductList.get(i);
                detailProduct.setDiscountAmout(billRequest.getListCart().get(i).getDiscountAmout());
                detailProduct.setQuantity(billRequest.getListCart().get(i).getQuantity());
                detailProductList.set(i, detailProduct);
            }

            cartService.deleteByListId(billRequest.getListCart());

            // add cac san pham vao bill
            List<DetailBill> dbl = detailBillService.createDetailBill(bill, detailProductList);
            if (dbl != null) {
                // update cac gia tri cua detial product
                for (int i = 0; i < detailProductList.size(); i++) {
                    DetailProduct currentProduct = detailProductService.getOneById(detailProductList.get(i).getId());
                    long quantity = currentProduct.getQuantity() - billRequest.getListCart().get(i).getQuantity();
                    currentProduct.setQuantity(quantity);
                    if (quantity == 0 || quantity < 0) {
                        currentProduct.setStatus(Constant.IN_ACTIVE);
                    }
                    detailProductService.updateDetailProduct(currentProduct, username);
                }
            }
            return Constant.SUCCESS;
        }
        throw new ValidationException(Constant.API001, "");
    }

    @Override
    public Object updateBill(BillRequest billRequest, String username) {
        // update bill history
        try {
            BillHistory billHistory = BillHistory.builder()
                    .bill(Bill.builder().id(billRequest.getId()).build())
                    .createdTime(DateUtil.dateToString4(new Date()))
                    .createdBy(username)
                    .description(billRequest.getDescription())
                    .status(billRequest.getStatus())
                    .build();
            billHistoryService.createBillHistory(billHistory);
            // update bill
            Bill bill = billRepository.findById(billRequest.getId()).get();
            bill.setId(bill.getId());
            bill.setAddress(billRequest.getAddress());
            bill.setPhone(billRequest.getPhone());
            bill.setMoneyShip(billRequest.getMoneyShip());
            bill.setStatus(billRequest.getStatus());
            billRepository.save(bill);
        } catch (Exception e) {
            return new ValidationException(Constant.API001, e.getMessage());
        }
        return Constant.SUCCESS;
    }

    @Override
    public Object getAllByCondition(BillRequest billRequest, String username) {
        Customer customer = customerService.findUserbyUsername(username);
        ListResponse<Bill> listResponse = new ListResponse<>();

        StringBuilder str = new StringBuilder();
        HashMap<String, Object> params = new HashMap<>();


        str.append(" SELECT b.* FROM bill b");
        str.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(billRequest.getCode())) {
            str.append(" AND b.code = :code ");
            params.put("code", billRequest.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(billRequest.getStatus())) {
            str.append(" AND b.status = :status ");
            params.put("status", billRequest.getStatus());
        }

        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdEmployee())) {
            str.append(" AND b.id_employee = :idEm ");
            params.put("idEm", billRequest.getIdEmployee());
        }

        str.append(" AND b.id_customer = :idCus ");
        params.put("idCus", customer.getId());

        if (!StringUtil.stringIsNullOrEmty(billRequest.getPayment())) {
            str.append(" AND b.payment = :payment ");
            params.put("payment", billRequest.getPayment());
        }

        str.append(" ORDER BY b.created_time DESC ");

        if (!StringUtil.stringIsNullOrEmty(billRequest.getPage())) {
            str.append(" LIMIT :page, :pageSize");
            if (billRequest.getPage() == 0) {
                params.put("page", 0);
            } else {
                params.put("page", (billRequest.getPage() * billRequest.getPageSize()));
            }
            params.put("pageSize", billRequest.getPageSize());
        }

        Query query = entityManager.createNativeQuery(str.toString(), Bill.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

        str.setLength(0);
        params.clear();

        str.append(" SELECT COUNT(*) FROM bill b");
        str.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(billRequest.getCode())) {
            str.append(" AND b.code = :code ");
            params.put("code", billRequest.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(billRequest.getStatus())) {
            str.append(" AND b.status = :status ");
            params.put("status", billRequest.getStatus());
        }

        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdEmployee())) {
            str.append(" AND b.id_employee = :idEm ");
            params.put("idEm", billRequest.getIdEmployee());
        }

        str.append(" AND b.id_customer = :idCus ");
        params.put("idCus", customer.getId());

        if (!StringUtil.stringIsNullOrEmty(billRequest.getPayment())) {
            str.append(" AND b.payment = :payment ");
            params.put("payment", billRequest.getPayment());
        }


        Query queryCount = entityManager.createNativeQuery(str.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);
        return listResponse;
    }

    @Override
    public Object getBillById(BillRequest billRequest) {
        return billRepository.findById(billRequest.getId());
    }

    @Override
    @Transactional
    public Object deleteDetailBill(BillRequest billRequest, String username) {
        // them bill history
        BillHistory billHistory = BillHistory.builder()
                .status(Constant.BILL.STATUS.EDIT)
                .bill(Bill.builder().id(billRequest.getId()).build())
                .description("Xoa san pham: " + billRequest.getProductName())
                .createdBy(username)
                .createdTime(DateUtil.dateToString4(new Date()))
                .build();
        billHistoryService.createBillHistory(billHistory);
        // lay thong tin bill tu sql
        Bill bill = billRepository.findById(billRequest.getId()).get();
        // lay thong tin detail bill
        DetailBill detailBill = detailBillService.getDetailBillById(billRequest.getIdDetailBill());
        BigDecimal detailBillPrice = detailBill.getPrice().multiply(BigDecimal.valueOf(detailBill.getQuantity()));

        bill.setTotalAmount(bill.getTotalAmount().subtract(detailBillPrice));
        billRepository.save(bill);
        // xoa detail bill
        return detailBillService.deleteDetailBill(billRequest.getIdDetailBill());
    }

    @Override
    public Object createBill(BillRequest billRequest) {
        String code = StringUtil.generateString(8);
        Boolean check = true;
        while (check) {
            code = StringUtil.generateString(8);
            check = billRepository.existsByCode(code);
        }
        Voucher voucher = null;
        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdVoucher())) {
            voucher = Voucher.builder().id(billRequest.getIdVoucher()).build();
        }
        // create bill
        Bill bill = billRepository.save(Bill.builder().code(code)
                .totalAmount(billRequest.getTotalAmout())
                .voucher(voucher)
                .receiver(billRequest.getReceiver())
                .discountAmount(billRequest.getDiscoutAmout())
                .phone(billRequest.getPhone())
                .address(billRequest.getAddress())
                .moneyShip(billRequest.getMoneyShip())
                .note(billRequest.getNote())
                .status(Constant.BILL.STATUS.WAIT_CONFIRM)
                .type(Constant.TYPE.DEVERILY)
                .payment(billRequest.getPayment())
                .discountAmount(billRequest.getDiscoutAmout())
                .createdTime(DateUtil.dateToString4(new Date())).build());
        if (bill != null) {
            // create bill_history
            String description = "";
            description = "Tạo hóa đơn, cho đơn ship";
            BillHistory billHistory = BillHistory.
                    builder().
                    bill(bill)
                    .status(Constant.BILL.STATUS.WAIT_CONFIRM)
                    .createdBy("Khach Le")
                    .createdTime(DateUtil.dateToString4(new Date()))
                    .description(description).build();
            billHistoryService.createBillHistory(billHistory);

            if (!StringUtil.stringIsNullOrEmty(billRequest.getIdVoucher())) {
                Voucher vou = voucherService.getOneById(billRequest.getIdVoucher());
                int voucherQuantity = vou.getQuantity() - 1;
                if (voucherQuantity == 0) {
                    vou.setStatus(Constant.IN_ACTIVE);
                }
                vou.setQuantity(voucherQuantity);
            }

            // add cac san pham vao bill
            List<DetailBill> dbl = detailBillService.createDetailBill(bill, billRequest.getListDetailProduct());
            if (dbl != null) {
                // update cac gia tri cua detial product
                for (DetailProduct dt : billRequest.getListDetailProduct()) {
                    DetailProduct currentProduct = detailProductService.getOneById(dt.getId());
                    long quantity = currentProduct.getQuantity() - dt.getQuantity();
                    currentProduct.setQuantity(quantity);
                    if (quantity == 0 || quantity < 0) {
                        currentProduct.setStatus(Constant.IN_ACTIVE);
                    }

                    if (dt.getQuantity() > currentProduct.getQuantity()) {
                        return new ValidationException(Constant.API001, "vuot qua so luong hang hoa co");
                    }

                    detailProductService.updateDetailProduct(currentProduct, "Khach Le");
                }
            }
            return Constant.SUCCESS;
        }
        throw new ValidationException(Constant.API001, "");
    }

}
