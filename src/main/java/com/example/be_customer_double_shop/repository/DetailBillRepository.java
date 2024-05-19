package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.dao.DetailBillDao;
import com.example.be_customer_double_shop.entity.DetailBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailBillRepository extends JpaRepository<DetailBill, Long> {

    @Query(value = " SELECT db.id AS id, p.code AS code, p.name AS name, dp.color AS color, dp.size AS size, db.quantity AS quantity, db.price AS price, db.discountAmout AS discountAmout FROM Bill b " +
            "INNER JOIN DetailBill db ON db.bill = b " +
            "INNER JOIN DetailProduct  dp ON dp = db.detailProduct " +
            "INNER JOIN Product p ON p = dp.product " +
            "WHERE b.id = :id")
    List<DetailBillDao> getDetailBillByBill(@Param("id") long id);

}
