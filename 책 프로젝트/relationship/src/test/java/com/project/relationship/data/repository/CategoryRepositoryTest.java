package com.project.relationship.data.repository;

import com.project.relationship.data.entity.Category;
import com.project.relationship.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest() {
        Product product = Product.builder()
                .name("펜")
                .price(2000)
                .stock(100)
                .build();

        productRepository.save(product);

        Category category = new Category();
        category.setCode("s1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        System.out.println(category);

        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for( Product fountProduct : products) {
            System.out.println(fountProduct);
        }
    }
}
