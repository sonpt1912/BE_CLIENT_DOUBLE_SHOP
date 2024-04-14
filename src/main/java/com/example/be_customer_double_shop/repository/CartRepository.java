package com.example.be_customer_double_shop.repository;


import com.example.be_customer_double_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT dp FROM DetailProduct dp INNER JOIN Product p ON p = dp.product ")
    List<Object> getProductByIdCustomer();

}
