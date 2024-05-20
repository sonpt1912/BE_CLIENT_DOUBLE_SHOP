package com.example.be_customer_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoucherRequest {

    private Long page;

    private Long pageSize;

    private String code;

    private Long idCustomer;


}

