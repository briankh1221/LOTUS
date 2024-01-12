package com.project.relationship.data.entity;

import com.project.relationship.data.dao.ProductDAO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_detail")
public class ProductDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_number")    // mapping 할 foreign key
    private Product product;
}
