package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.entity.Customer;

public interface MailService {

    Object sendOTP(CustomerRequest request);

    Object sendMailCreateAccount(Customer request);


}
