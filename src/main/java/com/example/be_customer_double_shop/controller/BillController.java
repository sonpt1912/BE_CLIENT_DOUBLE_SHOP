package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.BillHistoryService;
import com.example.be_customer_double_shop.service.BillService;
import com.example.be_customer_double_shop.service.DetailBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/bill")
public class BillController {


    @Autowired
    private BillService billService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private BillHistoryService billHistoryService;

    @Autowired
    private DetailBillService detailBillService;

    @PostMapping("/create-bill")
    public ResponseEntity createBill(@RequestBody BillRequest billRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws ParseException {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(billService.createBill(billRequest, username), HttpStatus.OK);
    }

    @PostMapping("/update-bill")
    public ResponseEntity updateBill(@RequestBody BillRequest billRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(billService.updateBill(billRequest, username), HttpStatus.OK);
    }

    @PostMapping("/get-bill-by-condition")
    public ResponseEntity getBillByCondition(@RequestHeader("Authorization") String token, @RequestBody BillRequest billRequest) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(billService.getAllByCondition(billRequest, username), HttpStatus.OK);
    }

    @PostMapping("/get-bill-history")
    public ResponseEntity getBillHistory(@RequestBody BillRequest billRequest) {
        return new ResponseEntity(billHistoryService.getBillHistoryByIdBill(billRequest), HttpStatus.OK);
    }

    @PostMapping("/get-bill-by-id")
    public ResponseEntity getBillById(@RequestBody BillRequest billRequest) {
        return new ResponseEntity(billService.getBillById(billRequest), HttpStatus.OK);
    }

    @PostMapping("/get-detail-bill")
    public ResponseEntity getDetailBill(@RequestBody BillRequest billRequest) {
        return new ResponseEntity(detailBillService.getDetailBill(billRequest), HttpStatus.OK);
    }

    @PostMapping("/delete-detail-bill")
    public ResponseEntity deleteDetailBill(@RequestBody BillRequest billRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(billService.deleteDetailBill(billRequest, username), HttpStatus.OK);
    }

}
