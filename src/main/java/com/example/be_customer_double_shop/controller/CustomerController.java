package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.entity.Address;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.AddressService;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.VoucherService;
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

    @Autowired
    private VoucherService voucherService;


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
    public ResponseEntity deleteAddress(@RequestBody Address address) {
        return new ResponseEntity(addressService.deleteAddress(address), HttpStatus.OK);
    }

    @PostMapping("/create-address")
    public ResponseEntity createAddress(@RequestBody Address address) {
        return new ResponseEntity(addressService.saveAddress(address), HttpStatus.OK);
    }

    @PostMapping("/update-address")
    public ResponseEntity updateAddress(@RequestBody Address address) {
        return new ResponseEntity(addressService.updateAddress(address), HttpStatus.OK);
    }

    // update address
    @GetMapping("/get-all-address")
    public ResponseEntity getAll(@RequestParam Long id) {
        return new ResponseEntity(addressService.getAllByIdCustomer(id), HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity updatePassword(@RequestBody Customer customer, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(customerService.updatePassword(customer, username), HttpStatus.OK);
    }

    // get voucher
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
