package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.CartRequest;

public interface CartService {

    Object createCart(CartRequest request, String username);

    Object updateCart(CartRequest cart);

    Object delete(long idCart);
}
