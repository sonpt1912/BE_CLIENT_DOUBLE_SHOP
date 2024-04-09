package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "voucher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 45)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "status")
    private Integer status;

    @Column(name = "minimum_order")
    private Long minimumOrder;

}