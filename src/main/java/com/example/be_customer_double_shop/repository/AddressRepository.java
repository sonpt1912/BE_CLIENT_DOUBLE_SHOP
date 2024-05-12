package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT a FROM Address a WHERE a.id = :id")
    Address getAddressById(@Param("id") long id);

    @Query(value = "SELECT a FROM Address a WHERE a.customer.id = :id")
    List<Address> getAllByCustomerId(long id);

    @Modifying
    @Query(value = "UPDATE Address\n" +
            "SET is_defaul = CASE\n" +
            "    WHEN id = :id THEN 0\n" +
            "    ELSE 1\n" +
            "END\n" +
            "WHERE id_customer = (SELECT id_customer FROM (SELECT id_customer FROM Address WHERE id = :id) AS subquery)\n",
            nativeQuery = true)
    void updateOtherAddresses(@Param("id") Long id);
}
