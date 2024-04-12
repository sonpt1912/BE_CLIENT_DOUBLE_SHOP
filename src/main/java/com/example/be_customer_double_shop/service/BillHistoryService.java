package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.entity.BillHistory;

public interface BillHistoryService {

    Object createBillHistory(BillHistory billHistory);

    Object getBillHistoryByIdBill(BillRequest billRequest);


}
