package com.example.be_customer_double_shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtRequest {

    private String username;

    private String password;

}
