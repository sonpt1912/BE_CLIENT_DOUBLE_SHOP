package com.example.be_customer_double_shop.dao;

import com.example.be_customer_double_shop.entity.Color;
import com.example.be_customer_double_shop.entity.Size;

import java.math.BigDecimal;

public interface DetailBillDao {

    Long getId();

    String getName();

    Color getColor();

    Size getSize();

    Long getQuantity();

    BigDecimal getPrice();

    BigDecimal getDiscountAmout();

}
