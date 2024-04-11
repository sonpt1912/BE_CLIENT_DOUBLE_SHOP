package com.example.be_customer_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private Long idProduct;

    private Long idColor;

    private Long idSize;

    private Long idBrand;

    private Long idCollar;

    private Long idCategory;

    private Long name;

    private int page;

    private int pageSize;

}
