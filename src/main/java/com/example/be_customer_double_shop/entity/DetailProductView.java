package com.example.be_customer_double_shop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "detail_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DetailProductView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonIgnore
    @ToString.Exclude
    private ProductView productView;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_collar")
    private Collar collar;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_material")
    private Material material;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Long price;

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

@OneToMany(mappedBy = "detailProduct")
@JsonIgnore
private List<DetailPromotion> detailPromotions;

    @Transient
    private boolean checkKM;
}
