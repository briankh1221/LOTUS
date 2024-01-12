package com.project.test.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private Long number;
    private String name;
    private int price;
    private int stock;

}
