package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.redisCache.OTPCache;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.repository.OTPCacheRepository;
import com.example.be_customer_double_shop.service.AuthService;
import com.example.be_customer_double_shop.service.MailService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private OTPCacheRepository cacheRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Object resetPassword(CustomerRequest userRequest) {
        if (cacheRepository.existsByKeyAndValue(new OTPCache(userRequest.getEmail(), userRequest.getOtp()))) {
            Customer customer = customerRepository.findCustomerByEmail(userRequest.getEmail());
            customer.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            return customerRepository.save(customer);
        }
        return new ValidationException(Constant.API001, "otp khong chinh xac");
    }

    @Override
    public Object forgotPasswrord(CustomerRequest userRequest) {
        Customer customer = customerRepository.findCustomerByEmail(userRequest.getEmail());
        cacheRepository.save(new OTPCache(customer.getEmail(), "123456"));
        userRequest.setOtp("123456");
        return mailService.sendOTP(userRequest);
    }
}
