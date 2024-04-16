package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.Size;

public interface SizeService {

    ListResponse<Size> getAllByConditon(SizeRequest request);

    String save(Size size);

    Object update(Size size);

}
