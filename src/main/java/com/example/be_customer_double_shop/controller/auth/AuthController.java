package com.example.be_customer_double_shop.controller.auth;

import com.example.be_customer_double_shop.dto.JwtResponse;
import com.example.be_customer_double_shop.dto.TokenRequest;
import com.example.be_customer_double_shop.dto.UserRequest;
import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.ChangePasswordRequest;
import com.example.be_customer_double_shop.dto.request.ResetPasswordRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.Employee;
import com.example.be_customer_double_shop.entity.google.UserInfoGoogle;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.EmailSerivce;
import com.example.be_customer_double_shop.service.OtpService;
import com.example.be_customer_double_shop.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmailSerivce emailService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody Customer customer) {
        try {
            userService.signUp(customer);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam String username, @RequestBody ChangePasswordRequest request) {
        try {
            userService.changePassword(username, request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody UserRequest request) {
        try {
            // Tìm kiếm người dùng bằng tên người dùng hoặc email
            Customer user = userService.findUserbyUsername(request.getUsername());

            // Kiểm tra xem người dùng có tồn tại không
            if (user != null) {
                // Tạo mã OTP
                String otp = otpService.generateOTP(user.getEmail());

                // Gửi mã OTP đến email người dùng
                emailService.sendOTPEmail(user.getEmail(), otp);

                // Trả về phản hồi thành công
                Map<String, Object> data = new HashMap<>();
                data.put("code", HttpStatus.OK.value());
                data.put("status", HttpStatus.OK.getReasonPhrase());
                data.put("message", "OTP sent successfully");
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                // Người dùng không tồn tại
                throw new ValidationException(Constant.NOT_FOUND, "User not found");
            }
        } catch (Exception e) {
            // Xảy ra lỗi
            throw new ValidationException(Constant.INTERNAL_SERVER_ERROR, "Failed to send OTP");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            // Kiểm tra xác thực mã OTP
            boolean isValidOTP = otpService.validateOTP(request.getEmail(), request.getOtp());

            if (isValidOTP) {
                // Thực hiện đặt lại mật khẩu
                Customer user = userService.findUserbyEmail(request.getEmail());
                String encodedPassword = passwordEncoder.encode(request.getNewPassword());
                user.setPassword(encodedPassword);
                userService.save(user);

                // Trả về phản hồi thành công
                Map<String, Object> data = new HashMap<>();
                data.put("code", HttpStatus.OK.value());
                data.put("status", HttpStatus.OK.getReasonPhrase());
                data.put("message", "Password reset successfully");
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                // Mã OTP không hợp lệ
                throw new ValidationException(Constant.INVALID_OTP, "Invalid OTP");
            }
        } catch (Exception e) {
            // Xảy ra lỗi
            throw new ValidationException(Constant.INTERNAL_SERVER_ERROR, "Failed to reset password");
        }
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest request) {
        try {
            Customer user = userService.findUserbyUsername(request.getUsername());
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
    public Object loginGoogle(@RequestBody TokenRequest crenditial) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserInfoGoogle user = (UserInfoGoogle) jwtProvider.getAllClaimsFromToken(crenditial.getCrenditial());
        Customer customer = userService.findUserbyEmail(user.getEmail());
        JwtResponse response = JwtResponse.builder()
                .access_token(jwtProvider.generateToken(customer)).build();
        return response;
    }

}
