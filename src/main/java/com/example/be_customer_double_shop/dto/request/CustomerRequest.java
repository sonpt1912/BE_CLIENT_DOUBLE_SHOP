package com.example.be_customer_double_shop.dto.request;

import com.example.be_customer_double_shop.entity.Address;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {

    private String phone;

    private String birthDay;

    private Integer gender;

    private Integer status;

    private String username;

    private String email;

    private String name;

    private String otp;

    private String password;

    private Address address;

}
