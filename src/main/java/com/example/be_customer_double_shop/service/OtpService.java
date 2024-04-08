package com.example.be_customer_double_shop.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class OtpService {
    private final Map<String, String> otpStore = new HashMap<>();

    // Tạo mã OTP cho một email cụ thể
    public String generateOTP(String email) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        String otpString = String.valueOf(otp);
        otpStore.put(email, otpString);
        return otpString;
    }

    // Xác thực mã OTP cho một email cụ thể
    public boolean validateOTP(String email, String otp) {
        String storedOTP = otpStore.get(email);
        return storedOTP != null && storedOTP.equals(otp);
    }
}
