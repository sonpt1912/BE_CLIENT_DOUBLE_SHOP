package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Size;
import com.example.be_customer_double_shop.repository.SizeRepository;
import com.example.be_customer_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public String save(Size size) {
        return null;
    }

    @Override
    public Object update(Size size) {
        return null;
    }

    @Override
    public Object getAllSize() {
        return sizeRepository.getAllSizes();
    }
}
