package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/get-all-brand")
    public ResponseEntity getAllBrand() {
        return new ResponseEntity(brandService.getAllBrandByStatus(), HttpStatus.OK);
    }

}
