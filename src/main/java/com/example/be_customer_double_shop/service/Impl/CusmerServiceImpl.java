package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.util.DateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CusmerServiceImpl implements CustomerService {


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

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Object createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Object updateCustomer(Customer customer) {
        customer.setUpdatedBy(customer.getUsername());
        customer.setUpdatedTime(DateUtil.dateToString4(new Date()));
        return customerRepository.save(customer);
    }
}
