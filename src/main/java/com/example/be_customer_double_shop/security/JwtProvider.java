package com.example.be_customer_double_shop.security;

import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.google.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {


    @Value("${spring.security.jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${spring.security.jwt.expiration_time}")
    private Integer EXPIRATION_TIME;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {

        token = token.substring(7);
        return getClaimFromToken(token, Claims::getSubject);

    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromTokenByKey(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromTokenByKey(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Object getAllClaimsFromToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserInfo payloadObject = objectMapper.readValue(payload, UserInfo.class);

            return payloadObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //check if the token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    //generate token for user
    public String generateToken(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", customer.getName());
        claims.put("email", customer.getEmail());
        claims.put("phone", customer.getPhone());
        claims.put("gender", customer.getGender());
        claims.put("birth_day", customer.getBirthDay());
        claims.put("status", customer.getStatus());
        claims.put("created_by", customer.getCreatedBy());
        claims.put("updated_by", customer.getUpdatedBy());
        claims.put("created_time", customer.getCreatedTime());
        claims.put("updated_time", customer.getUpdatedTime());
        return doGenerateToken(claims, customer.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60000000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }


}
