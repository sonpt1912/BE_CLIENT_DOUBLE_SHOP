package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.entity.Product;

public interface ProductService {

    Object getByCondition(ProductRequest request);

    Product getProductById(Long id);

}
