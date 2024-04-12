package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.entity.Voucher;

import java.text.ParseException;

public interface VoucherService {

    Voucher getOneId(Long id);

    String save(Voucher voucher, String username);

    Object update(Voucher voucherRequest, String username) throws ParseException;

    String saveAll(Voucher voucher, String username);

    Voucher getByCode(String code);

}
