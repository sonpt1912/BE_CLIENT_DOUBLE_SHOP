package com.example.be_customer_double_shop.service.Impl;


import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.util.Constant;
import com.example.be_customer_double_shop.util.DateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CusmerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer findUserbyUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer findUserbyEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Object createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer.setUpdatedBy(customer.getUsername());
        customer.setUpdatedTime(DateUtil.dateToString4(new Date()));

        return customerRepository.save(customer);
    }

    @Override
    public Object updatePassword(Customer customer, String username) {
        Customer custom = customerRepository.findCustomerByUsername(username);
        if (passwordEncoder.matches(customer.getPassword().trim(), custom.getPassword())) {
            custom.setPassword(passwordEncoder.encode(customer.getNewPassword().trim()));
            return customerRepository.save(custom);
        }
        throw new ValidationException(Constant.API001, "password cu khong chinh xac");

    }

    @Transactional
    public void updateOtherAddresses(Long id) {
        customerRepository.updateOtherAddresses(id);
    }


}
