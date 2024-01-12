package com.project.relationship.data.repository;

import com.project.relationship.data.entity.Product;
import com.project.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderReposiitory providerReposiitory;

    @Test
    void relationshipTest1() {

        Provider provider = Provider.builder()
                .name("삼성물산")
                .build();
        providerReposiitory.save(provider);

        Product product = Product.builder()
                .name("가위")
                .price(5000)
                .stock(500)
                .provider(provider)
                .build();
        productRepository.save(product);

        System.out.println(productRepository.findById(1L)
                .orElseThrow(RuntimeException::new));

        System.out.println(productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider());

    }

    @Test
    void relationshipTest2() {
        Provider provider = Provider.builder()
                .name("상사상사")
                .build();

        providerReposiitory.save(provider);

        Product product1 = Product.builder()
                .name("펜")
                .price(2000)
                .stock(100)
                .provider(provider)
                .build();

        Product product2 = Product.builder()
                .name("가방")
                .price(5000)
                .stock(200)
                .provider(provider)
                .build();

        Product product3 = Product.builder()
                .name("노트")
                .price(3000)
                .stock(1000)
                .provider(provider)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerReposiitory.findById(provider.getId()).get().getProductList();

        for(Product product : products) {
            System.out.println(product);
        }
    }

}
