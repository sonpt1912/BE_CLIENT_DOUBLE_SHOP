package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRespository extends JpaRepository<Color, Long> {
}
