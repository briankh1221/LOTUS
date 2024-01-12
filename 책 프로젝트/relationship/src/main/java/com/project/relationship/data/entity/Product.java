package com.project.relationship.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

}
