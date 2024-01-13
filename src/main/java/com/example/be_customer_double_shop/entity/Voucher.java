package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "voucher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voucher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_promotion")
    private Promotion promotion;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "discountAmout")
    private Long discountAmout;

    @Column(name = "discountPercentage")
    private Integer discountPercentage;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

}
