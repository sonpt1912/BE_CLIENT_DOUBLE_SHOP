package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detail_bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_detail_product", referencedColumnName = "id")
    private DetailProduct detailProduct;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "discount_amout", nullable = false)
    private BigDecimal discountAmout;

    @Column(name = "status", nullable = false)
    private Integer status;
}
