package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Material;
import com.example.be_customer_double_shop.repository.MaterialRespository;
import com.example.be_customer_double_shop.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRespository materialRespository;

    @Override
    public List<Material> getAllByStatus() {
        return materialRespository.findByStatus(0);
    }
}
