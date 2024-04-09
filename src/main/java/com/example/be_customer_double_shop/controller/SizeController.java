package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.config.EnableWrapResponse;
import com.example.be_customer_double_shop.dto.request.SizeRequest;
import com.example.be_customer_double_shop.entity.Size;
import com.example.be_customer_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
@EnableWrapResponse
public class SizeController {

    @Autowired
    private SizeService sizeService;


    @PostMapping("/get-size-by-condition")
    public ResponseEntity getAllSize(@RequestBody SizeRequest request) {
        return new ResponseEntity(sizeService.getAllByConditon(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Size size) {
        return new ResponseEntity(sizeService.save(size), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Size size) {
        return ResponseEntity.ok(sizeService.update(size));
    }

}
