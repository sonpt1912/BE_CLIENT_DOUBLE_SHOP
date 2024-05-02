package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.entity.Size;

public interface SizeService {

    String save(Size size);

    Object update(Size size);

    Object getAllSize();

}
