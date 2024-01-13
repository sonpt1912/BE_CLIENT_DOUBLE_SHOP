package com.example.be_customer_double_shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {

    private String jwtToken;

    private String username;

    private String fullName;

}
