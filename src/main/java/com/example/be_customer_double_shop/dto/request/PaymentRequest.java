package com.example.be_customer_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest implements Serializable {

    private Long orderCode;

    private BigDecimal amount;

    private String description;

    private String buyerName;

    private String buyerEmail;

    private String buyerPhone;

    private String buyerAddress;

    private String cancelUrl;

    private String returnUrl;

    private Integer expiredAt;

    private String signature;

    public String toString() {
        return "{'orderCode':'" + orderCode + "','amount':'" + amount + "','description':'" + description + "','cancelUrl':'" + cancelUrl + "','returnUrl':'" + returnUrl + "'}";
    }

}
