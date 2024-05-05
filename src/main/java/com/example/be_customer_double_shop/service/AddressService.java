package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.entity.Address;

public interface AddressService {

    Address saveAddress(Address address);

    String deleteAddress(Address address);

    Address updateAddress(Address address);

    Address getAddressById(long id);

}
