package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM Address WHERE id_customer = :id",nativeQuery = true)
    List<Address> findAddressByCustomerId(@Param("id") Long idCustomer);
}
