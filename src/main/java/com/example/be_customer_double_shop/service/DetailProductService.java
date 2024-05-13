package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.entity.DetailProduct;

import java.util.List;

public interface DetailProductService {

    Object updateDetailProduct(DetailProduct detailProduct, String username);

    DetailProduct getOneById(Long id);

    DetailProduct getDetailProductByColorSizeAndProduct(ProductRequest productRequest);

    List<DetailProduct> getAllDetailProductByIdCart(List<Cart> cartList);

}
