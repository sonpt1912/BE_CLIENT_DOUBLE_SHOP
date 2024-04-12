package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Bill;
import com.example.be_customer_double_shop.entity.BillHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillHistoryRepository extends JpaRepository<BillHistory, Long> {

    List<BillHistory> findAllByBill(Bill bill);


}
