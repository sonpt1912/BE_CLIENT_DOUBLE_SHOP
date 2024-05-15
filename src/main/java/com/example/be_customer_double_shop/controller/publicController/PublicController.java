package com.example.be_customer_double_shop.controller.publicController;

import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private BillService billService;

    @PostMapping("/create-bill")
    public ResponseEntity createBill(@RequestBody BillRequest billRequest) {
        return new ResponseEntity(billService.createBill(billRequest), HttpStatus.OK);
    }

}
