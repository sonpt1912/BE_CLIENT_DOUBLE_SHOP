package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Collar;
import com.example.be_customer_double_shop.repository.CollarRespository;
import com.example.be_customer_double_shop.service.CollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollarServiceImpl implements CollarService {

    @Autowired
    private CollarRespository collarRespository;


    @Override
    public List<Collar> getAllByCollarByStatus() {
        return collarRespository.findAllByStatus(0);
    }
}
