package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.AddressService;
import com.example.be_customer_double_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;


    @PostMapping("/user-info")
    public ResponseEntity getUserinfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(customerService.findUserbyUsername(username), HttpStatus.OK);
    }

    @PostMapping("/update-user-infor")
    public ResponseEntity getUserinfo(@RequestBody Customer customer) {
        return new ResponseEntity(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    // xoa address
    @PostMapping("/delete-address")
    public ResponseEntity deleteAddress() {
        return new ResponseEntity(addressService, HttpStatus.OK);
    }


    // update address
    @GetMapping("/get-all-address")
    public ResponseEntity getAll(@RequestParam Long id){
        return new ResponseEntity(addressService.getAllByIdCustomer(id),HttpStatus.OK);
    }

}
