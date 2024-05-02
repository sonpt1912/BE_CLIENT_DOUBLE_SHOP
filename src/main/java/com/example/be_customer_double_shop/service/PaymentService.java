package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.PaymentRequest;

public interface PaymentService {

    Object createPaymentLink(PaymentRequest request);

    Object checkPayment(PaymentRequest request);

}
