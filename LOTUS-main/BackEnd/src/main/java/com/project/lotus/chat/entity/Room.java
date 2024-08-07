package com.project.lotus.chat.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.chat.dto.RoomDto;
import com.project.lotus.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long roomIdx;

    @ManyToOne
    @JoinColumn(name ="idx")
    private User buyer;

    @ManyToOne
    @JoinColumn(name ="product_idx")
    private Product product;

    public static Room from (RoomDto.Request roomDto, User buyer, Product product) {

        return Room.builder()
                .buyer(buyer)
                .product(product)
                .build();
    }
}
