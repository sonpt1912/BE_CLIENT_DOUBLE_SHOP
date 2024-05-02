package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category c WHERE c.status = :status")
    List<Category> getAllCategoryByStatus(@Param("status") int status);

}
