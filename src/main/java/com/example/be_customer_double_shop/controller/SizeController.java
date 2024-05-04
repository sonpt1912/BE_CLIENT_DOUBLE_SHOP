package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/get-all-size")
    public ResponseEntity getAllSize() {
        return new ResponseEntity(sizeService.getAllSize(), HttpStatus.OK);
    }

}
