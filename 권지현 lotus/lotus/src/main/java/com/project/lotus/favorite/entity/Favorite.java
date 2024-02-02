package com.project.lotus.favorite.entity;


import com.project.lotus.auth.entity.User;
import com.project.lotus.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @NotNull
    @OneToOne
    @JoinColumn(name = "product_idx")
    private Product product;
}
