package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Address;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT a FROM Address a WHERE a.id = :id")
    Address getAddressById(@Param("id") long id);

}
