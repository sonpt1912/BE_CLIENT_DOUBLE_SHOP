package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.*;
import com.example.be_customer_double_shop.repository.BillRepository;
import com.example.be_customer_double_shop.repository.CustomerVoucherRepository;
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
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    private CustomerVoucherRepository customerVoucherRepository;


    @Autowired
    private CustomerService customerService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object createBill(BillRequest billRequest, String username) throws ParseException {
        String code = UUID.randomUUID().toString();
        Boolean check = true;
        while (check) {
            code = UUID.randomUUID().toString();
            check = billRepository.existsByCode(code);
        }

        Voucher voucher = null;
        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdVoucher())) {
            voucher = Voucher.builder().id(billRequest.getIdVoucher()).build();
        }

        // create bill
        Bill bill = billRepository.save(Bill.builder().code(code)
                .totalAmount(billRequest.getTotalAmout())
                .customer(customerService.findUserbyUsername(username))
                .voucher(voucher)
                .discountAmount(billRequest.getDiscoutAmout())
                .phone(billRequest.getPhone())
                .address(billRequest.getAddress())
                .note(billRequest.getNote())
                .status(billRequest.getStatus())
                .payment(billRequest.getPayment())
                .discountAmount(billRequest.getDiscoutAmout())
                .type(billRequest.getType()).build());

        if (bill != null) {
            // create bill_history
            String description = "Tạo hóa đơn, cho đơn ship";

            BillHistory billHistory = BillHistory.builder().bill(bill)
                    .status(billRequest.getStatus())
                    .createdBy(username)
                    .createdTime(DateUtil.dateToString4(new Date()))
                    .description(description).build();
            billHistoryService.createBillHistory(billHistory);

            if (!StringUtil.stringIsNullOrEmty(billRequest.getIdVoucher())) {
                Voucher vou = voucherService.getOneId(billRequest.getIdVoucher());
                int voucherQuantity = vou.getQuantity() - 1;
                if (voucherQuantity == 0) {
                    vou.setStatus(Constant.IN_ACTIVE);
                }
                vou.setQuantity(voucherQuantity);
                voucherService.update(vou, username);
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

        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdCustomer())) {
            str.append(" AND b.id_customer = :idCus ");
            params.put("idCus", billRequest.getIdCustomer());
        }

        if (!StringUtil.stringIsNullOrEmty(billRequest.getPayment())) {
            str.append(" AND b.payment = :payment ");
            params.put("payment", billRequest.getPayment());
        }


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

        if (!StringUtil.stringIsNullOrEmty(billRequest.getIdCustomer())) {
            str.append(" AND b.id_customer = :idCus ");
            params.put("idCus", billRequest.getIdCustomer());
        }

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

}
