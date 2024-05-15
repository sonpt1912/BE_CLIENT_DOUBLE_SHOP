package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.BillRequest;

import java.text.ParseException;

public interface BillService {

    Object createBill(BillRequest billRequest, String username) throws ParseException;

    Object updateBill(BillRequest billRequest, String username);

    Object getAllByCondition(BillRequest billRequest, String username);

    Object getBillById(BillRequest billRequest);

    Object deleteDetailBill(BillRequest billRequest, String username);

    // public
    Object createBill(BillRequest billRequest);

}
