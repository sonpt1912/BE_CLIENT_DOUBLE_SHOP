package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.VoucherRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.CustomerVoucherService;
import com.example.be_customer_double_shop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/customer-voucher")
public class CustomerVoucherController {
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerVoucherService customerVoucherService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VoucherService voucherService;

    @PostMapping("/get-all-by-condition")
    public ResponseEntity getAllCustomerVoucher(@RequestBody VoucherRequest request, @RequestHeader("Authorization") String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        Customer customer=customerService.findUserbyUsername(username);
        return new ResponseEntity(customerVoucherService.getAll(request,customer.getId()), HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity check(@RequestBody VoucherRequest request){
//        String username = jwtProvider.getUsernameFromToken(token);
//        Customer customer=customerService.findUserbyUsername(username);
        return new ResponseEntity(voucherService.getByCodeAndCustomerId(request),HttpStatus.OK);
    }
}
