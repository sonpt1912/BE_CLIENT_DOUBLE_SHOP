package com.example.be_customer_double_shop.dao;

import com.example.be_customer_double_shop.entity.*;

public interface DetailProductDao {

    Long getId();

    Color getColor();

    Product getProduct();

    Size getSize();

    Brand getBrand();

    Collar getCollar();

    Category getCategory();

    Material getMaterial();

    Long getQuantity();

    Integer getStatus();

    String getCreatedBy();

    String getCreatedTime();

    String getUpdatedBy();

    String getUpdatedTime();

    Long getPrice();

    Long getDiscountAmount();

}
