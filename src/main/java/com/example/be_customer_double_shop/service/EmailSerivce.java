package com.example.be_customer_double_shop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSerivce {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOTPEmail(String toEmail, String otp) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Your OTP for Password Reset");
        helper.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
    }
}
