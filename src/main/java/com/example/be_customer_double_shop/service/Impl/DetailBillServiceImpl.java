package com.example.be_customer_double_shop.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.entity.Bill;
import com.example.be_customer_double_shop.entity.DetailBill;
import com.example.be_customer_double_shop.entity.DetailProduct;
import com.example.be_customer_double_shop.repository.DetailBillRepository;
import com.example.be_customer_double_shop.service.DetailBillService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailBillServiceImpl implements DetailBillService {

    @Autowired
    private DetailBillRepository detailBillRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<DetailBill> createDetailBill(Bill bill, List<DetailProduct> listProduct) {
        List<DetailBill> listDetailBill = new ArrayList<>();
        for (DetailProduct detailProduct : listProduct) {
            DetailBill detailBill = DetailBill.builder()
                    .bill(bill)
                    .quantity(detailProduct.getQuantity())
                    .price(detailProduct.getPrice())
                    .detailProduct(DetailProduct.builder().id(detailProduct.getId()).build())
                    .discountAmout(detailProduct.getDiscountAmout())
                    .status(Constant.ACTIVE.intValue())
                    .build();
            listDetailBill.add(detailBill);
        }
        return detailBillRepository.saveAll(listDetailBill);
    }

    @Override
    public Object getDetailBill(BillRequest billRequest) {
        return detailBillRepository.getDetailBillByBill(billRequest.getId());
    }

    @Override
    public Object deleteDetailBill(long idDetailBill) {
        try {
            detailBillRepository.deleteById(idDetailBill);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constant.SUCCESS;
    }

    @Override
    public DetailBill getDetailBillById(long id) {
        return detailBillRepository.findById(id).get();
    }
}
