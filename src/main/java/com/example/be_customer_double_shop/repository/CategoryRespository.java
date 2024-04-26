package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> {
}
