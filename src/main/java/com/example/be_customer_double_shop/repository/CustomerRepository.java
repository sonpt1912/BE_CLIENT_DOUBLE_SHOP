package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.username = :username")
    Customer findCustomerByUsername(@Param("username") String username);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(long id);

    Boolean existsByEmail(String email);

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
