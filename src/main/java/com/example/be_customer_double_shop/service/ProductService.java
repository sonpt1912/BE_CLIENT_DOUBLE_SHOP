package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.ProductRequest;

public interface ProductService {

    Object getByCondition(ProductRequest request);

}
