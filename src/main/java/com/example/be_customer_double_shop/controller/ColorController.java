package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.ColorRequest;
import com.example.be_customer_double_shop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/get-all-color")
    public ResponseEntity getAllColor() {
        return new ResponseEntity(colorService.getAllByStatus(), HttpStatus.OK);
    }

    @PostMapping("/get-all-color-by-condition")
    public ResponseEntity getAllByCondition(@RequestBody ColorRequest colorRequest) {
        return new ResponseEntity(colorService.getAllByCondition(colorRequest), HttpStatus.OK);
    }

}
