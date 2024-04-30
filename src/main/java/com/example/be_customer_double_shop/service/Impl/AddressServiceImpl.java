package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Address;
import com.example.be_customer_double_shop.repository.AddressRepository;
import com.example.be_customer_double_shop.service.AddressService;
import com.example.be_customer_double_shop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        address.setCreatedBy(address.getCustomer().getName());
        address.setCreatedTime(DateUtil.dateToString4(new Date()));
        return addressRepository.save(address);
    }
}
