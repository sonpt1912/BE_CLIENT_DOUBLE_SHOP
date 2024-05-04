package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Color;
import com.example.be_customer_double_shop.repository.ColorRespository;
import com.example.be_customer_double_shop.service.ColorService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRespository colorRespository;

    @Override
    public List<Color> getAllByStatus() {
        return colorRespository.findAllByStatus(Constant.ACTIVE);
    }
}
