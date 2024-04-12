package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.dao.DetailProductDao;
import com.example.be_customer_double_shop.entity.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProductRepository extends JpaRepository<DetailProduct, Long> {

    @Query("SELECT dt.id AS id, " +
            "       dt.color AS color," +
            "       dt.product AS product, " +
            "       dt.size AS size, " +
            "       dt.brand AS brand, " +
            "       dt.collar AS collar, " +
            "       dt.category AS category, " +
            "       dt.material AS material, " +
            "       dt.quantity AS quantity, " +
            "       dt.status AS status, " +
            "       dt.createdBy AS createdBy, " +
            "       dt.createdTime AS createdTime, " +
            "       dt.updatedBy AS updatedBy, " +
            "       dt.updatedTime AS updatedTime, " +
            "       dt.price AS price, " +
            " CASE      WHEN (p.discountAmount > 0)" +
            "               THEN dt.price - p.discountAmount" +
            "           WHEN (p.discountPercent > 0)" +
            "               THEN (dt.price / (100 * p.discountPercent))" +
            "           ELSE 0" +
            "           END AS discountAmount " +
            " FROM DetailProduct dt " +
            "         LEFT JOIN DetailPromotion dp on dt = dp.detailProduct  " +
            "         LEFT JOIN Promotion p on dp.promotion = p " +
            " WHERE dt.product.id = :id AND dt.status = 0")
    List<DetailProductDao> getAllDetailProduct(@Param("id") Long id);

}
