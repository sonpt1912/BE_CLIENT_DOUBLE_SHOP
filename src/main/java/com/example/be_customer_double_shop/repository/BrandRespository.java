package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRespository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT b FROM Brand b WHERE b.status = :status")
    List<Brand> findAllByStatus(@Param("status") int status);

}
