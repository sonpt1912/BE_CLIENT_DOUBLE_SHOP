package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.entity.Bill;
import com.example.be_customer_double_shop.entity.DetailBill;
import com.example.be_customer_double_shop.entity.DetailProduct;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DetailBillService {

    List<DetailBill> createDetailBill(Bill bill, List<DetailProduct> listProduct);

    Object getDetailBill(BillRequest billRequest) throws InterruptedException, ExecutionException;

    Object deleteDetailBill(long idDetailBill);

    DetailBill getDetailBillById(long id);


}
