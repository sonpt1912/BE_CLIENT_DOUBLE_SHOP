package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/get-all-material")
    public ResponseEntity getAllMaterial() {
        return new ResponseEntity(materialService.getAllByStatus(), HttpStatus.OK);
    }

}
