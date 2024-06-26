package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @Column(name = "code")
    private String code;

    @Column(name = "phone")
    private String phone;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "discount_amout")
    private BigDecimal discountAmout;

    @Column(name = "total_amout")
    private BigDecimal totalAmout;

    @Column(name = "confirmDate")
    private String confirmDate;

    @Column(name = "ship_date")
    private String shipDate;

    @Column(name = "receive_date")
    private String recevieDate;

    @Column(name = "note")
    private String note;

    @Column(name = "money_ship")
    private BigDecimal moneyShip;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;


}
