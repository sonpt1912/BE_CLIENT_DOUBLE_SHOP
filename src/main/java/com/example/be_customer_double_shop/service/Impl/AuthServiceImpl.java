package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.entity.Address;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.redisCache.OTPCache;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.repository.OTPCacheRepository;
import com.example.be_customer_double_shop.service.AddressService;
import com.example.be_customer_double_shop.service.AuthService;
import com.example.be_customer_double_shop.service.MailService;
import com.example.be_customer_double_shop.util.Constant;
import com.example.be_customer_double_shop.util.DateUtil;
import com.example.be_customer_double_shop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private OTPCacheRepository cacheRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Object resetPassword(CustomerRequest userRequest) {
        try {
            OTPCache otpCache = cacheRepository.findById(userRequest.getEmail()).get();
            if (otpCache.getKey().equals(userRequest.getEmail()) && otpCache.getValue().equals(userRequest.getOtp())) {
                Customer customer = customerRepository.findCustomerByEmail(userRequest.getEmail());
                customer.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                return customerRepository.save(customer);
            }
        } catch (Exception e) {
            return new ValidationException(Constant.API001, "otp khong chinh xac");
        }
        return new ValidationException(Constant.API001, "otp khong chinh xac");
    }

    @Override
    public Object forgotPasswrord(CustomerRequest userRequest) {
        Customer customer = customerRepository.findCustomerByEmail(userRequest.getEmail());
        String otp = StringUtil.generateString(6);
        cacheRepository.save(new OTPCache(customer.getEmail(), otp));
        userRequest.setOtp(otp);
        return mailService.sendOTP(userRequest);
    }

    @Override
    public Object register(CustomerRequest userRequest) {
        Customer customer = customerRepository.findCustomerByEmail(userRequest.getEmail());
        if (customer == null) {

            Customer newCustomer = Customer.builder()
                    .email(userRequest.getEmail())
                    .name(customer.getName())
                    .username(customer.getEmail())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .createdBy(userRequest.getEmail())
                    .createdTime(DateUtil.dateToString4(new Date()))
                    .phone(userRequest.getPhone())
                    .birthDay(userRequest.getBirthDay())
                    .gender(userRequest.getGender())
                    .build();
            customerRepository.save(newCustomer);

            Address address = userRequest.getAddress();
            address.setCustomer(customer);
            address.setDefaul(Constant.isDefault);
            addressService.saveAddress(address);
            return Constant.SUCCESS;
        }

        throw new ValidationException(Constant.API001, "Email hoac user da ton tai");
    }
}
