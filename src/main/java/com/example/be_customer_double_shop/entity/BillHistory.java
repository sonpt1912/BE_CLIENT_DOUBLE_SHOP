package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bill_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    private Bill bill;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private String createdTime;

}
