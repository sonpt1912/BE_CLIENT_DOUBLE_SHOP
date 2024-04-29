package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.entity.Voucher;

import java.util.List;

public interface VoucherService {

    List<Voucher> getAllVoucherByUserLogin(String username);

    Voucher getByCode(String code);

}
