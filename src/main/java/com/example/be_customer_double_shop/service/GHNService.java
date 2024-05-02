package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.dto.request.GHNRequest;

public interface GHNService {

    Object getFeeShipping(GHNRequest ghnRequest);

    Object getProvince();

    Object getDistrict(String provinceId);

    Object getWard(int ward);

}
