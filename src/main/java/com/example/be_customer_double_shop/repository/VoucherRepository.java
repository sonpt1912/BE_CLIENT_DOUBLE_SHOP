package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {
    @Query(value = "SELECT CODE FROM voucher WHERE code = :code", nativeQuery = true)
    String checkCodeExits(@Param("code") String code);

    @Query(value = "SELECT * FROM voucher WHERE code = :code", nativeQuery = true)
    Voucher getVoucherByCode(@Param("code") String code);

    List<Voucher> findAllByStatus(int t);
}
