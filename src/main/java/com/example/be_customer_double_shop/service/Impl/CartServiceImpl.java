package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.repository.CartRepository;
import com.example.be_customer_double_shop.service.CartService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Object save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Object update(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Object delete(long idCart) {
        try {
            cartRepository.deleteById(idCart);
        } catch (Exception e) {
            return new ValidationException(Constant.API001, "");
        }
        return Constant.SUCCESS;

    }
}
