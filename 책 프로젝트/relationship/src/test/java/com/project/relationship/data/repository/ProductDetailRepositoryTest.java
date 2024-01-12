package com.project.relationship.data.repository;

import com.project.relationship.data.entity.Product;
import com.project.relationship.data.entity.ProductDetail;
import com.project.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndReadTest1() {
        Product product = Product.builder()
                .name("스프링부트 JPA")
                .price(5000)
                .stock(500)
                .build();

        productRepository.save(product);    // save를 하면 product에 id, Base Entity 등이 담긴다

        ProductDetail productDetail = ProductDetail.builder()
                .description("스프링 부트와 JPA를 함께 볼 수 있는 책")
                .product(product)
                .build();

        productDetailRepository.save(productDetail);

        // 생성한 데이터 조회

        System.out.println(product);

        System.out.println(productDetailRepository.findById(productDetail.getId()).get().getProduct());

        System.out.println(productDetailRepository.findById(productDetail.getId()).get());
    }


}
