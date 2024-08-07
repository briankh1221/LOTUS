package com.project.lotus.user.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "favorite")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="favorite_idx")
    private Long favoriteIdx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_idx")
    private Product product;

    // User(Entity), Product(Entity) -> Favorite(Entity)로 변환 *24.01.31 jihyun
    public static Favorite from(User user, Product product) {

        return Favorite.builder()
                .user(user)
                .product(product)
                .build();
    }
}
