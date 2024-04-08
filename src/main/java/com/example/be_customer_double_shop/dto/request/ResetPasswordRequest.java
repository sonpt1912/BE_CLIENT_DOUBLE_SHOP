package com.example.be_customer_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResetPasswordRequest {
    private String email;
    private String otp;
    private String newPassword;

}
