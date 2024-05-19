package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.VoucherRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.CustomerVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-voucher")
public class CustomerVoucherController {
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerVoucherService customerVoucherService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/get-all-by-condition")
    public ResponseEntity getAllCustomerVoucher(@RequestBody VoucherRequest request, @RequestHeader("Authorization") String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        Customer customer=customerService.findUserbyUsername(username);
        return new ResponseEntity(customerVoucherService.getAll(request,customer.getId()), HttpStatus.OK);
    }
}
