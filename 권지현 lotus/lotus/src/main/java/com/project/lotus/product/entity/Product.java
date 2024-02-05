package com.project.lotus.product.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.common.enums.DeliveryFee;
import com.project.lotus.common.enums.DeliveryMethod;
import com.project.lotus.common.enums.ProductStatus;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.product.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseTimeEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productIdx;

    @NotNull
    @OneToOne
    @JoinColumn(name ="user_idx")
    private User user;

    @NotNull
    private Long categoryIdx;

    @NotNull
    private String productName;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryFee deliveryFee;

    @NotNull
    private String price;

    @NotNull
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @NotNull
    private String address;

    @NotNull
    private LocalDateTime postingDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    // ProductDto.Request -> Product(Entity)로 변환 *24.01.26 jihyun
    public static Product from(ProductDto.Request productDto, User user) {

        return Product.builder()
                .user(user)
                .categoryIdx(productDto.getCategoryIdx())
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .deliveryFee(productDto.getDeliveryFee())
                .price(productDto.getPrice())
                .image(productDto.getImage())
                .productStatus(productDto.getProductStatus())
                .deliveryMethod(productDto.getDeliveryMethod())
                .address(productDto.getAddress())
                .postingDate(LocalDateTime.parse(productDto.getPostingDate()))
                .transactionStatus(productDto.getTransactionStatus())
                .build();
    }

    public void modifyProduct(ProductDto.Request productDto) {

        if (productDto.getCategoryIdx() != null) {
            this.categoryIdx = productDto.getCategoryIdx();
        }
        if  (productDto.getProductName() != null) {
            this.productName = productDto.getProductName();
        }
        if (productDto.getDescription() != null) {
            this.description = productDto.getDescription();
        }
        if (productDto.getDeliveryFee() != null) {
            this.deliveryFee = productDto.getDeliveryFee();
        }
        if (productDto.getPrice() != null) {
            this.price = productDto.getPrice();
        }
        if (productDto.getProductStatus() != null) {
            this.productStatus = productDto.getProductStatus();
        }
        if (productDto.getDeliveryMethod() != null) {
            this.deliveryMethod = productDto.getDeliveryMethod();
        }
        if (productDto.getAddress() != null) {
            this.address = productDto.getAddress();
        }
        if (productDto.getPostingDate() != null) {
            this.postingDate = LocalDateTime.parse(productDto.getPostingDate());
        }
        if (productDto.getTransactionStatus() != null) {
            this.transactionStatus = productDto.getTransactionStatus();
        }
    }
}
