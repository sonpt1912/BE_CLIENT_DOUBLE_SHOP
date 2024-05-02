package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Collar;
import com.example.be_customer_double_shop.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRespository extends JpaRepository<Material, Long> {

    @Query(value = "SELECT m FROM Material m WHERE m.status = :status")
    List<Material> findByStatus(@Param("status") int status);

}
