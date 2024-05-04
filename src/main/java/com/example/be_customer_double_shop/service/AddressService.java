package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.entity.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address address);

    List<Address> getAllByIdCustomer(Long idCustomer);

}
