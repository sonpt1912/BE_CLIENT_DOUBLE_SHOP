package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.response.AddressResponse;
import com.example.be_customer_double_shop.entity.Address;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.repository.AddressRepository;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.service.AddressService;
import com.example.be_customer_double_shop.util.Constant;
import com.example.be_customer_double_shop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GHNServiceImpl ghn;

    @Override
    public Address add(Address ad, String username) {
        Customer customer=customerRepository.findCustomerByUsername(username);
        ad.setCreatedBy(customer.getName());
        ad.setCreatedTime(DateUtil.dateToString4(new Date()));
        ad.setCustomer(customer);
        ad.setDefaul(1);

       return addressRepository.save(ad);
    }

    @Override
    public Address saveAddress(Address address) {
//        address.setCreatedBy(address.getCustomer().getName());
        address.setCreatedTime(DateUtil.dateToString4(new Date()));
        return addressRepository.save(address);
    }

    @Override
    public String deleteAddress(Address address) {
        Address add = this.getAddressById(address.getId());
        addressRepository.delete(add);
        return Constant.SUCCESS;
    }

    @Override
    public Address updateAddress(Address address) {
        Address add = this.getAddressById(address.getId());
        add.setCity(address.getCity());
        add.setProvince(address.getProvince());
        add.setDistrict(address.getDistrict());
        add.setDescription(address.getDescription());
//        add.setDefaul(address.getDefaul());
        return addressRepository.save(add);
    }

    @Override
    public Address getAddressById(long id) {
        return addressRepository.getAddressById(id);
    }

    @Override
    public List<Address> getAllByIdCustomer(long id) {
        return addressRepository.getAllByCustomerId(id);
    }


}
