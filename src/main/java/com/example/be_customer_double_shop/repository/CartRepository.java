package com.example.be_customer_double_shop.repository;


import com.example.be_customer_double_shop.dao.DetailBillDao;
import com.example.be_customer_double_shop.dao.DetailProductDao;
import com.example.be_customer_double_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT c FROM Cart c WHERE c.id = :id ")
    Cart getCartById(@Param("id") long id);

    @Query(value = "SELECT c.id AS id," +
            "       p.name AS name," +
            "       dp.size AS size," +
            "       dp.color AS color," +
            "       dp.price AS price," +
            "       MAX(CASE" +
            "               WHEN p.discountAmount > 0 THEN p.discountAmount" +
            "               WHEN p.discountPercent > 0 THEN (100 * p.discountPercent) / dp.price" +
            "               ELSE 0" +
            "           END) AS discountAmount " +
            " FROM Cart c " +
            "         INNER JOIN Customer cs on c.customer = cs" +
            "         INNER JOIN DetailProduct dp on c.detailProduct = dp" +
            "         INNER JOIN Size s on dp.size = s" +
            "         INNER JOIN Color c2 on dp.color = c2" +
            "         LEFT JOIN DetailPromotion d on dp = d.detailProduct" +
            "         INNER JOIN Promotion p on d.promotion = p " +
            "WHERE cs.username = :username " +
            "GROUP BY c.id, p.name, dp.size, dp.color, dp.price")
    List<DetailProductDao> getAllDetailProductFromCart(@Param("username") String username);

}
