package com.project.relationship.data.dao.Impl;

import com.project.relationship.data.dao.ProductDAO;
import com.project.relationship.data.entity.Product;
import com.project.relationship.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getReferenceById(number); // getReferenceById vs FindById

        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;

        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);

            updatedProduct = productRepository.save(product);       // update 아닌 save로 실행. save의 경우 변동값이 감지되면 알아서 update로 실행
        }
        else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        }
        else {
            throw new Exception();
        }
    }


}
