package com.example.be_customer_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorRequest {

    private long idProduct;

    private long idSize;

    private long page;

    private long pageSize;

}
