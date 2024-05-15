package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.entity.CustomerVoucher;

public interface CustomerVoucherService {

    CustomerVoucher updateCustomerVoucher(CustomerVoucher customerVoucher);

    CustomerVoucher getByCustomerAndVoucher(long idCustomer, long idVoucher);

}
