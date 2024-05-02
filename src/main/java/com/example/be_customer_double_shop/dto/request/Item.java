package com.example.be_customer_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String name;

    private int quantity;

    private int height;

    private int length;

    private int weight;

    private int width;


}
