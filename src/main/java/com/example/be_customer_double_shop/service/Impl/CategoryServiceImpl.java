package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Category;
import com.example.be_customer_double_shop.repository.CategoryRespository;
import com.example.be_customer_double_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;

    @Override
    public List<Category> getAllByStatus() {
        return categoryRespository.getAllCategoryByStatus(0);
    }

}
