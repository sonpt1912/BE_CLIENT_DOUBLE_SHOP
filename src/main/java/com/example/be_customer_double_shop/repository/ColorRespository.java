package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Collar;
import com.example.be_customer_double_shop.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRespository extends JpaRepository<Color, Long> {

    @Query(value = "SELECT c FROM Color c WHERE c.status = :status")
    List<Color> findAllByStatus(@Param("status") int status);

}
