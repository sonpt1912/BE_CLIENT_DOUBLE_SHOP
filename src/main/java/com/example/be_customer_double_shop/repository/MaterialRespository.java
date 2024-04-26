package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Category;
import com.example.be_customer_double_shop.entity.Collar;
import com.example.be_customer_double_shop.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRespository extends JpaRepository<Collar, Long> {
}
