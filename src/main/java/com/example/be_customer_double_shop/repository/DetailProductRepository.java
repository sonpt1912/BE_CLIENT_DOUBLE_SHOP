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
            "       pd.brand AS brand, " +
            "       pd.collar AS collar, " +
            "       pd.category AS category, " +
            "       pd.material AS material, " +
            "       dt.quantity AS quantity, " +
            "       dt.status AS status, " +
            "       dt.createdBy AS createdBy, " +
            "       dt.createdTime AS createdTime, " +
            "       dt.updatedBy AS updatedBy, " +
            "       dt.updatedTime AS updatedTime, " +
            "       dt.price AS price, " +
            "      MAX(CASE " +
            "               WHEN p.discountAmount > 0 THEN p.discountAmount " +
            "               WHEN p.discountPercent > 0 THEN (100 * p.discountPercent) / dt.price " +
            "               ELSE 0 " +
            "           END) AS discountAmount " +
            " FROM DetailProduct dt " +
            "         INNER JOIN Product pd ON pd = dt.product " +
            "         LEFT JOIN DetailPromotion dp on dt = dp.detailProduct  " +
            "         LEFT JOIN Promotion p on dp.promotion = p " +
            " WHERE dt.product.id = :id AND (dt.status = 0 AND  p.status = 0 OR p.status IS NULL) ")
    List<DetailProductDao> getAllDetailProduct(@Param("id") Long id);


    @Query(value = "SELECT dt FROM DetailProduct dt WHERE dt.color.id = :idColor AND dt.size.id = :idSize AND dt.product.id = :idProduct ")
    DetailProduct getDetailProductByColorAndSizeAndProduct(@Param("idColor") long idColor, @Param("idSize") long idSize, @Param("idProduct") long idProduct);

    @Query(value = "SELECT dt FROM DetailProduct dt INNER JOIN Cart c ON c.detailProduct.id = dt.id WHERE c.id IN ( :listCart )")
    List<DetailProduct> getAllDetailProductByListId(@Param("listCart") List<Long> listCart);

}
