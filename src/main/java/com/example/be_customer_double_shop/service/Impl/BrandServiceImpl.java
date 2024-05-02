package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Brand;
import com.example.be_customer_double_shop.repository.BrandRespository;
import com.example.be_customer_double_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRespository brandRespository;

    @Override
    public List<Brand> getAllBrandByStatus() {
        return brandRespository.findAllByStatus(0);
    }
}
