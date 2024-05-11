package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.entity.Address;

import java.util.List;

public interface AddressService {
    Address add(Address ad,String username);

    Address saveAddress(Address address);

    String deleteAddress(Address address);

    Address updateAddress(Address address);

    Address getAddressById(long id);

    List<Address> getAllByIdCustomer(long id);

    String checkAddressDefaul(long id);

}
