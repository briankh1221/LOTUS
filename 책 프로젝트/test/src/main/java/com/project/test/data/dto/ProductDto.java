package com.project.test.data.dto;

import lombok.*;
import org.springframework.stereotype.Component;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDto {

    private String name;
    private int price;
    private int stock;

}
