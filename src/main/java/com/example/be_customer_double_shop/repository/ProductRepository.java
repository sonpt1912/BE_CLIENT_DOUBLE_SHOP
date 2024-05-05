package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "SELECT p FROM DetailProduct p WHERE p.product.id = :idProduct AND p.size.id = :idSize " +
//            "GROUP BY p.id")
//    List<Object> getDetailProductByProduct(Product product);

    @Query(value = "SELECT p FROM Product p WHERE p.id = :idProduct")
    Product getProductById(@Param("idProduct") long id);

}
