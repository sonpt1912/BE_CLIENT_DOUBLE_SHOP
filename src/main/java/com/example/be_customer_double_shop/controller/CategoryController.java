package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-all-category")
    public ResponseEntity getAllCategory() {
        return new ResponseEntity(categoryService.getAllByStatus(), HttpStatus.OK);
    }

}
