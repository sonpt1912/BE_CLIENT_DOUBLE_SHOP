package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.username = :username")
    Customer findCustomerByUsername(@Param("username") String username);

    Customer findCustomerByEmail(String email);

    Boolean existsByEmail(String email);

}
