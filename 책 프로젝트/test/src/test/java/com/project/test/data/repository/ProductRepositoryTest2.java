package com.project.test.data.repository;

import com.project.test.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTest2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("SpringBoot CRUD test 입니다")
    public void basicCRUDTest() {
        /* create */
        // given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();

        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then

        Assertions.assertThat(savedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(savedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(savedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(savedProduct.getStock())
                .isEqualTo(givenProduct.getStock());


        /* read */
        // when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);  // 이부분 안붙이면 type이 다름

        // then
        Assertions.assertThat(selectedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(selectedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(selectedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(selectedProduct.getStock())
                .isEqualTo(givenProduct.getStock());


        /* update */
        // when
        Product foundProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);
        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertEquals(updatedProduct.getName(), "장난감");


        /* delete */
        // when
        productRepository.delete(updatedProduct);

        // then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());


    }



}
