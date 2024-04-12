package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.repository.AddressRepository;
import com.example.be_customer_double_shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

}
