package com.example.be_customer_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private long id;

    private long isCustomer;

    private long idDetailProduct;

    private long quantity;

}
