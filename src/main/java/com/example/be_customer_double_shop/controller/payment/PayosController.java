package com.example.be_customer_double_shop.controller.payment;

import com.example.be_customer_double_shop.dto.request.PaymentRequest;
import com.example.be_customer_double_shop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PayosController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-link")
    public ResponseEntity createPaymentLink(@RequestBody PaymentRequest request) {
        return new ResponseEntity(paymentService.createPaymentLink(request), HttpStatus.OK);
    }

    @PostMapping("/check-payment")
    public ResponseEntity checkPayment(@RequestBody PaymentRequest request) {
        return new ResponseEntity(paymentService.checkPayment(request), HttpStatus.OK);
    }

}
