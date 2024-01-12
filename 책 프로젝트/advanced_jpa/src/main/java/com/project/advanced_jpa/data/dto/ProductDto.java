package com.project.advanced_jpa.data.dto;

import lombok.*;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {

    private String name;
    private int price;
    private int stock;

}
