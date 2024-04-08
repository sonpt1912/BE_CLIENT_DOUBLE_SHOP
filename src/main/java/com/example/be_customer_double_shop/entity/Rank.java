package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rank")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "from")
    private Long from;

    @Column(name = "to", nullable = false)
    private Long to;

    @Column(name = "percent")
    private Integer percent;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @Column(name = "updated_by", nullable = false, length = 45)
    private String updatedBy;

    @Column(name = "created_by", length = 45)
    private String createdBy;

}