package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query(value = "SELECT f.* FROM Product p JOIN Favorite f ON p.id = f.id_product WHERE f.id_customer = :id", nativeQuery = true)
    List<Favorite> findByCustomerId(@Param("id") Long id);
}
