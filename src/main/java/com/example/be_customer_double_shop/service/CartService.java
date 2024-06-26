package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.CartRequest;
import com.example.be_customer_double_shop.entity.Cart;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CartService {

    Object deleteByListId(List<Cart> listIdCart);

    Object createCart(CartRequest request, String username);

    Object updateCart(CartRequest cart);

    Object delete(long idCart);

    Object getAllProductFromCart(String username) throws InterruptedException, ExecutionException;
}
