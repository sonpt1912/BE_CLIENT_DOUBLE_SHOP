package com.example.be_customer_double_shop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detail_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @Transient
    private BigDecimal discountAmout;

}
