package com.example.be_customer_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FavoriteRequest {

    private Long id;

    private Long idProduct;

    private Long idCustomer;


}
