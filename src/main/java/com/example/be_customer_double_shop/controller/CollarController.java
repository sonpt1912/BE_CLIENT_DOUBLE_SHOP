package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.service.CollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collar")
public class CollarController {

    @Autowired
    private CollarService collarService;

    @GetMapping("/get-all-collar")
    public ResponseEntity getAllCollar() {
        return new ResponseEntity(collarService.getAllByCollarByStatus(), HttpStatus.OK);
    }

}
