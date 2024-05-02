package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.PaymentRequest;
import com.example.be_customer_double_shop.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payos.client_id}")
    private String clientId;

    @Value("${payos.api_key}")
    private String apiKey;

    @Value("${payos.base_url}")
    private String url;

    static final String checksumKey = "3dae4252bb8463b177bf145fc7c5c3a5e960d72e7483352287cba46770439c5f";


    @Override
    public Object createPaymentLink(PaymentRequest request) {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("x-client-id", clientId);
        header.add("x-api-key", apiKey);
        request.setSignature(generateSignature(request.toString()));
        ObjectMapper obj = new ObjectMapper();
        Object object = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, header), Object.class);
        Map<String, Object> response = obj.convertValue(object, Map.class);

        return response.get("body");
    }

    public Object checkPayment(PaymentRequest request) {
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("x-client-id", clientId);
        header.add("x-api-key", apiKey);
        request.setSignature(generateSignature(request.toString()));
        ObjectMapper obj = new ObjectMapper();

        Object object = restTemplate.exchange(url + "/" + request.getOrderCode(), HttpMethod.GET, new HttpEntity<>(request, header), Object.class);
        Map<String, Object> response = obj.convertValue(object, Map.class);

        return response.get("body");
    }

    private static String generateSignature(String transaction) {
        try {
            JSONObject jsonObject = new JSONObject(transaction);
            Iterator<String> sortedIt = sortedIterator(jsonObject.keys(), (a, b) -> a.compareTo(b));
            StringBuilder transactionStr = new StringBuilder();
            while (sortedIt.hasNext()) {
                String key = sortedIt.next();
                String value = jsonObject.get(key).toString();
                transactionStr.append(key);
                transactionStr.append('=');
                transactionStr.append(value);
                if (sortedIt.hasNext()) {
                    transactionStr.append('&');
                }
            }
            String signature = new HmacUtils("HmacSHA256", checksumKey).hmacHex(transactionStr.toString());
            return signature;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Iterator<String> sortedIterator(Iterator<?> it, Comparator<String> comparator) {
        List<String> list = new ArrayList<>();
        while (it.hasNext()) {
            list.add((String) it.next());
        }
        Collections.sort(list, comparator);
        return list.iterator();
    }
}
