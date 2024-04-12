package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.CustomerVoucher;
import com.example.be_customer_double_shop.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerVoucherRepository extends JpaRepository<CustomerVoucher, Long> {

    List<CustomerVoucher> findAllByVoucher(Voucher voucher);

    CustomerVoucher findByVoucherId(Long id);
}
