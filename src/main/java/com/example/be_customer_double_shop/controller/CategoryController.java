package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-all-category")
    public ResponseEntity getAllCategory() {
        return new ResponseEntity(categoryService.getAllByStatus(), HttpStatus.OK);
    }

}
