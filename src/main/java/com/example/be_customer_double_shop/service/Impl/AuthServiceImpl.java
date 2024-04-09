package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.UserRequest;
import com.example.be_customer_double_shop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {



    @Override
    public Object resetPassword(UserRequest userRequest) {
        return null;
    }

    @Override
    public Object forgotPasswrord(UserRequest userRequest) {
        return null;
    }
}
