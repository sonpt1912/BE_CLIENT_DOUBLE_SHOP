package com.example.be_customer_double_shop.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Value("${cloudiary.cloud_name}")
    private String cloudName;

//    @Value("${cloudiary.api_key")
//    private String apiKey;

    @Value("${cloudiary.secure}")
    private String secure;

    @Value("${cloudiary.api_secret}")
    private String apiSecret;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Cloudinary getCloudiary() {

        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", "463632651578279");
        config.put("api_secret", apiSecret);
        config.put("secure", secure);
        return new Cloudinary(config);
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }

}
