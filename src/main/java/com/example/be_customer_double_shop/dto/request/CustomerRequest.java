package com.example.be_customer_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {
    private String phone;
    private Integer status;
    private String username;
    private String email;
    private String name;

}
