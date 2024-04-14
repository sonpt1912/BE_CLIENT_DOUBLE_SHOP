package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/delete-product-from-cart")
    public ResponseEntity deleteProductFromCart(@RequestBody Cart cart) {
        return new ResponseEntity(cartService.delete(cart.getId()), HttpStatus.OK);
    }

    @PostMapping("/get-product-from-cart")
    public ResponseEntity getProductFromCart(@RequestBody Cart cart) {
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/add-product-to-cart")
    public ResponseEntity addProductToCart(@RequestBody Cart cart) {
        return new ResponseEntity(cartService.save(cart), HttpStatus.OK);
    }


}
