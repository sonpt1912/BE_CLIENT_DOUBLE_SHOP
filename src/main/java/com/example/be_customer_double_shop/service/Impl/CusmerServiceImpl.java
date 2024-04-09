package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CusmerServiceImpl implements CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Customer findUserbyUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer findUserbyEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
