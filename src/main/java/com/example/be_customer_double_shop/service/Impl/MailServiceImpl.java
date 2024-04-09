package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.service.MailService;
import com.example.be_customer_double_shop.util.Constant;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailServiceImpl implements MailService {


    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Object sendOTP(CustomerRequest request) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(sender, "DOUBLE SHOP");
            helper.setSubject("THAY DOI MAT KHAU");


            Context context = new Context();
            context.setVariable("otp", request.getOtp());

            String template = templateEngine.process("forgot-password", context);
            helper.setText(template, true);

            javaMailSender.send(message);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public Object sendMailCreateAccount(Customer customer) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(sender, "DOUBLE SHOP");
            helper.setSubject("TAO TAI KHOAN");


            Context context = new Context();
            context.setVariable("account", customer.getUsername());
            context.setVariable("password", passwordEncoder.encode(customer.getPassword()));

            String template = templateEngine.process("create-account", context);
            helper.setText(template, true);

            javaMailSender.send(message);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
