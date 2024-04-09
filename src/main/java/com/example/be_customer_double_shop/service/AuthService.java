package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.UserRequest;

public interface AuthService {

    Object resetPassword(UserRequest userRequest);

    Object forgotPasswrord(UserRequest userRequest);

}
