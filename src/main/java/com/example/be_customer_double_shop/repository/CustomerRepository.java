package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByUsername(String username);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    Boolean existsByEmail(String email);
}
