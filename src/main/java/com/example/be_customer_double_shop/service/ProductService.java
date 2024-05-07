package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.entity.DetailProduct;
import com.example.be_customer_double_shop.entity.Product;

public interface ProductService {

    Object getAllByCondition(ProductRequest request)  throws Exception;

    Product getProductById(long id);

    Object getDetailProductByProduct(ProductRequest request);

    Product getProduct(long id) throws Exception;

}
