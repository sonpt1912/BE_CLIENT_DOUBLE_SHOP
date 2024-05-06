package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.SizeRequest;
import com.example.be_customer_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/get-all-size")
    public ResponseEntity getAllSize() {
        return new ResponseEntity(sizeService.getAllSize(), HttpStatus.OK);
    }

    @PostMapping("/get-all-by-conditoin")
    public ResponseEntity getAllByCondition(@RequestBody SizeRequest sizeRequest) {
        return new ResponseEntity(sizeService.getAllByCondition(sizeRequest), HttpStatus.OK);
    }

}
