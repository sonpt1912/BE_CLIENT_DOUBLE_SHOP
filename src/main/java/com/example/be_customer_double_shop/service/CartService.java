package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.entity.Cart;

public interface CartService {

    Object save(Cart cart);

    Object update(Cart cart);

    Object delete(long idCart);
}
