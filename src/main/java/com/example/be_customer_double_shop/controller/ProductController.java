package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.service.CartService;
import com.example.be_customer_double_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/get-all-by-condition")
    public ResponseEntity getAllByCondition(ProductRequest request) {
        return new ResponseEntity(productService.getByCondition(request), HttpStatus.OK);
    }

    //TODO: get top 3 - theo cac loai du kien ( ban chay nhat, moi... )
    @PostMapping("/get-top-3-by-condition")
    public ResponseEntity getTop3ByConditioin(ProductRequest request) {
        return new ResponseEntity(productService.getByCondition(request), HttpStatus.OK);
    }


}
