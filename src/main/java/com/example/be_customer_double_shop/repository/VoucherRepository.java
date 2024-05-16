package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "SELECT * FROM voucher WHERE code = :code", nativeQuery = true)
    Voucher getVoucherByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM voucher WHERE id = :id", nativeQuery = true)
    Voucher getVoucherById(@Param("id") long id);

    @Query(value = "SELECT v FROM Voucher v " +
            "         LEFT JOIN CustomerVoucher cv on v = cv.voucher " +
            "         INNER JOIN Customer c on cv.customer = c " +
            "WHERE c.username = :username AND v.status = 0 AND v.quantity > 0 AND cv.usageDate IS NULL ")
    List<Voucher> findAllByUsernameLogin(@Param("username") String username);
}
