package com.example.be_customer_double_shop.controller.auth;

import com.example.be_customer_double_shop.dto.JwtResponse;
import com.example.be_customer_double_shop.dto.TokenRequest;
import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.google.UserInfo;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.AuthService;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.MailService;
import com.example.be_customer_double_shop.util.Constant;
import com.example.be_customer_double_shop.util.DateUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private MailService mailService;

    // forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody CustomerRequest request) {
        return new ResponseEntity(authService.forgotPasswrord(request), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody CustomerRequest request) {
        return new ResponseEntity(authService.resetPassword(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody CustomerRequest request) {
        try {
            Customer user = new Customer();
            if (user.getUsername() != null) {
                user = userService.findUserbyUsername(request.getUsername());
            }
            if (user.getEmail() != null) {
                user = userService.findUserbyEmail(request.getUsername());
            }
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtProvider.generateToken(user);
                Map<String, Object> data = new HashMap<>();
                data.put("code", HttpStatus.OK.value());
                data.put("status", HttpStatus.OK.getReasonPhrase());
                data.put("message", Constant.SUCCESS);
                JwtResponse response = JwtResponse.builder()
                        .access_token(token).build();
                data.put("data", response);
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                throw new ValidationException(Constant.UNAUTHORIZED, Constant.USERNAME_OR_PASSWORD);
            }
        } catch (Exception e) {
            throw new ValidationException(Constant.UNAUTHORIZED, Constant.USERNAME_OR_PASSWORD);
        }
    }

    @PostMapping("/google")
    @Transactional
    public Object loginGoogle(@RequestBody TokenRequest crenditial) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserInfo user = (UserInfo) jwtProvider.getAllClaimsFromToken(crenditial.getCrenditial());
        // neu chua ton tai thi tao ra thang moi
        if (!userService.existsByEmail(user.getEmail())) {
            Customer customer = Customer.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(Constant.DEFAULT_PASSWORD))
                    .createdBy(user.getEmail())
                    .status(Constant.ACTIVE)
                    .username(user.getEmail())
                    .createdTime(DateUtil.dateToString4(new Date()))
                    .gender(0)
                    .birthDay(DateUtil.dateToString4(new Date()))
                    .build();
            userService.createCustomer(customer);
            mailService.sendMailCreateAccount(customer);
        }
        Customer customer = userService.findUserbyEmail(user.getEmail());
        JwtResponse response = JwtResponse.builder()
                .access_token(jwtProvider.generateToken(customer)).build();
        return response;
    }


}
