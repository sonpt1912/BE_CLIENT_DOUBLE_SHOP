package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Collar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollarRespository extends JpaRepository<Collar, Long> {

    @Query(value = "SELECT c FROM Collar c WHERE c.status = :status")
    List<Collar> findAllByStatus(@Param("status") int status);

}
