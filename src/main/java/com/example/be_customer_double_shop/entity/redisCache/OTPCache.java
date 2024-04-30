package com.example.be_customer_double_shop.entity.redisCache;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "OTP_CACHE", timeToLive = 120L)
public class OTPCache implements Serializable {

    @Id
    @Indexed
    private String key;

    private String value;

}
