package com.project.lotus.product.repository;

import com.project.lotus.product.dto.ProductCategoryDto;
import com.project.lotus.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByCategoryIdx(Long CategoryIdx);
}
