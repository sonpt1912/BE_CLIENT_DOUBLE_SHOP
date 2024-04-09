package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.entity.Customer;

public interface CustomerService {

    Customer findUserbyUsername(String username);

    Customer findUserbyEmail(String email);

}
