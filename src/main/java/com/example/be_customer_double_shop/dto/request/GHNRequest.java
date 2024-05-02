package com.example.be_customer_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GHNRequest {

    private int toDistrictId;

    private String toWardCode;

    private int height;

    private int length;

    private int weight;

    private int width;

    private int insuranceValue;

    private int coupon;

    private List<Item> items;



}
