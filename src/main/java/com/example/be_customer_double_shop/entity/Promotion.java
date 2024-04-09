package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "promotion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Promotion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @Column(name = "status")
    private Integer status;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<DetailPromotion> detailPromotions;


}
