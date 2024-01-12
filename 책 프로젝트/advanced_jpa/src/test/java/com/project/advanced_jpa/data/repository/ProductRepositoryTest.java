package com.project.advanced_jpa.data.repository;

import com.project.advanced_jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest() {
        // given
        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .build();

        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        System.out.println(productRepository.findAllByNameOrderByNumberAsc("펜"));
        System.out.println(productRepository.findAllByNameOrderByNumberDesc("펜"));

        System.out.println("=========================================================");

        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"))));
        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock"))));

        System.out.println("========================================================= next paging");

        Page<Product> productPage1 = productRepository.findByName("펜", PageRequest.of(0, 2));
        Page<Product> productPage2 = productRepository.findByName("펜", PageRequest.of(1, 2));
        System.out.println(productPage1);
        System.out.println(productPage1.getContent());
        System.out.println(productPage2.getContent());

        Page<Product> productPage3 = productRepository.findByName("펜", PageRequest.of(0, 2, Sort.by(Order.asc("price"))));
        System.out.println(productPage3);
        System.out.println(productPage3.getContent());

    }

    @Test
    public void queryAnnotationTest() {
        System.out.println(productRepository.findByName("펜"));
        System.out.println(productRepository.findByNameParam("펜"));

        List<List<Object>> objects = productRepository.findByNameParam2("펜");

        for(List<Object> object : objects) {
            System.out.println(object.get(0));
            System.out.println(object.get(1));
            System.out.println(object.get(2));
        }
        System.out.println("========================================");
    }


    @Test
    public void auditingTest() {
        System.out.println("auditintTest 시작");
        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product savedProduct = productRepository.save(product);

        System.out.println(savedProduct.getName());
        System.out.println(savedProduct.getCreatedAt());
        System.out.println(savedProduct.getUpdatedAt());
    }
}
