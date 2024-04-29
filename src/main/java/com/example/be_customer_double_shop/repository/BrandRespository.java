package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRespository extends JpaRepository<Brand, Long> {
}