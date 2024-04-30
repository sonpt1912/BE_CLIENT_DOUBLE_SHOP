package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.CustomerRequest;

public interface AuthService {

    Object resetPassword(CustomerRequest userRequest);

    Object forgotPasswrord(CustomerRequest userRequest);

    Object register(CustomerRequest userRequest);

}
