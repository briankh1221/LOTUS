package com.project.lotus.product.service.impl;

import com.project.lotus.common.exception.CustomException;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.repository.ProductCategoryRepository;
import com.project.lotus.product.repository.ProductRepository;
import com.project.lotus.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.lotus.common.exception.ErrorCode.PRODUCTCATEGORY_NOT_EXISTS;
import static com.project.lotus.common.exception.ErrorCode.PRODUCT_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    // 상품 상세 정보 조회 *24.01.24 jihyun
    @Override
    public ProductDto.Response findProductDetails(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        return ProductDto.Response.from(product);
    }

    // 카테고리별 상품 목록 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findProductList(Long categoryIdx) {

        if (!productCategoryRepository.existsById(categoryIdx)) {
            throw new CustomException(PRODUCTCATEGORY_NOT_EXISTS);
        }

        List<Product> productList = productRepository.findAllByCategoryIdx(categoryIdx);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 최신 상품 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findRecentProductList() {

        List<Product> productList = productRepository.findFirst10ByOrderByPostingDateDesc();

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 베스트 찜 상품 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findBestProductList() {
        return null;
    }
}
