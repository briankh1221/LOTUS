package com.project.test.service.impl;

import com.project.test.data.dto.ProductDto;
import com.project.test.data.dto.ProductResponseDto;
import com.project.test.data.entity.Product;
import com.project.test.data.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest () {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("getProduct Test 입니다")
    void getProductTest() {
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

       verify(productRepository).findById(123L);

    }

    @Test
    @DisplayName("saveProduct Test 입니다")
    void saveProductTest() {
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234));

        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());

    }


}
