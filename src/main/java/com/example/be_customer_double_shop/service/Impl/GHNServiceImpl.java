package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.GHNRequest;
import com.example.be_customer_double_shop.service.GHNService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GHNServiceImpl implements GHNService {

    @Value("${ghn.token}")
    private String token;

    @Value("${ghn.shopId}")
    private String shopId;

    @Value("${ghn.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Object getFeeShipping(GHNRequest ghnRequest) {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Token", token);
        header.add("ShopId", shopId);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("service_id", 53320);
        requestBody.put("service_type_id", 5);
        requestBody.put("to_district_id", ghnRequest.getToDistrictId());
        requestBody.put("to_ward_code", ghnRequest.getToWardCode());
        requestBody.put("width", ghnRequest.getWidth());
        requestBody.put("length", ghnRequest.getLength());
        requestBody.put("weight", ghnRequest.getWeight());
        requestBody.put("height", ghnRequest.getHeight());
        requestBody.put("insurance_value", 0);
        requestBody.put("coupon", null);
        requestBody.put("items", ghnRequest.getItems());
        ObjectMapper obj = new ObjectMapper();
        Object response = restTemplate.exchange(url + "/v2/shipping-order/fee", HttpMethod.POST, new HttpEntity<>(requestBody, header), Object.class);
        Map<String, Object> map = obj.convertValue(response, Map.class);
        return map.get("body");
    }

    @Override
    public Object getProvince() {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Token", token);
//        header.add("ShopId", shopId);
        ObjectMapper obj = new ObjectMapper();
        Object response = restTemplate.exchange(url + "/master-data/province", HttpMethod.GET, new HttpEntity<>(null, header), Object.class);
        Map<String, Object> map = obj.convertValue(response, Map.class);
        return map.get("body");
    }

    @Override
    public Object getDistrict(String provinceId) {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Token", token);
//        header.add("ShopId", shopId);

        Map<String, Object> body = new HashMap<>();
        body.put("province_id", provinceId);

        ObjectMapper obj = new ObjectMapper();
        Object response = restTemplate.exchange(url + "/master-data/district", HttpMethod.GET, new HttpEntity<>(body, header), Object.class);
        Map<String, Object> map = obj.convertValue(response, Map.class);
        return map.get("body");
    }

    @Override
    public Object getWard(int ward) {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Token", token);
//        header.add("ShopId", shopId);

        Map<String, Object> body = new HashMap<>();
        body.put("district_id", ward);

        ObjectMapper obj = new ObjectMapper();
        Object response = restTemplate.exchange(url + "/master-data/ward", HttpMethod.POST, new HttpEntity<>(body, header), Object.class);
        Map<String, Object> map = obj.convertValue(response, Map.class);
        return map.get("body");
    }


}
