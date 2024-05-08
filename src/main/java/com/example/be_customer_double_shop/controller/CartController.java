package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.CartRequest;
import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/delete-product-from-cart")
    public ResponseEntity deleteProductFromCart(@RequestBody Cart cart) {
        return new ResponseEntity(cartService.delete(cart.getId()), HttpStatus.OK);
    }

    @PostMapping("/get-product-from-cart")
    public ResponseEntity getProductFromCart(@RequestHeader("Authorization") String token) throws ExecutionException, InterruptedException {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(cartService.getAllProductFromCart(username), HttpStatus.OK);
    }

    @PostMapping("/add-product-to-cart")
    public ResponseEntity addProductToCart(@RequestBody CartRequest request, @RequestHeader("Authorization") String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(cartService.createCart(request, username), HttpStatus.OK);
    }

    @PostMapping("/update-cart")
    public ResponseEntity updateCart(@RequestBody CartRequest request) {
        return new ResponseEntity(cartService.updateCart(request), HttpStatus.OK);
    }


}
