package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.entity.Bill;
import com.example.be_customer_double_shop.entity.BillHistory;
import com.example.be_customer_double_shop.repository.BillHistoryRepository;
import com.example.be_customer_double_shop.service.BillHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillHistoryServiceImpl implements BillHistoryService {

    @Autowired
    private BillHistoryRepository billHistoryRepository;

    @Override
    public Object createBillHistory(BillHistory billHistory) {
        return billHistoryRepository.save(billHistory);
    }

    @Override
    public Object getBillHistoryByIdBill(BillRequest billRequest) {
        return billHistoryRepository.findAllByBill(Bill.builder().id(billRequest.getId()).build());
    }


}
