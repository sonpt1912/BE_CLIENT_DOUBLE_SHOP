package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/get-voucher-by-user-login")
    public ResponseEntity getVoucherByUserLogin(@RequestHeader("Authorization") String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(voucherService.getAllVoucherByUserLogin(username), HttpStatus.OK);
    }

    @PostMapping("/get-voucher-by-code")
    public ResponseEntity getVoucherbyCode(@RequestBody String code) {
        return new ResponseEntity(voucherService.getByCode(code), HttpStatus.OK);
    }

}
