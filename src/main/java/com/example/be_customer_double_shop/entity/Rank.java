package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_rank")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Rank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "from")
    private Long from;

    @Column(name = "to")
    private Long to;

    @Column(name= "percent")
    private Integer percent;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;
}
