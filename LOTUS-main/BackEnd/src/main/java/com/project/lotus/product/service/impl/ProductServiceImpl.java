package com.project.lotus.product.service.impl;

import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.product.dto.ProductPageDto;
import com.project.lotus.user.repository.FavoriteRepository;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.repository.ProductRepository;
import com.project.lotus.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.project.lotus.common.exception.ErrorCode.PRODUCT_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;

    // 키워드 검색 *24.02.08 jihyun
    public Map<String, Object> productList(Pageable pageable, String Keyword) {

        Page<Product> productList = productRepository.findByProductNameContainingOrTitleContainingOrDescriptionContaining(Keyword, Keyword, Keyword, pageable);

        ProductPageDto productPageDto = ProductPageDto.builder()
                .startPage(Math.max(0, productList.getPageable().getPageNumber() - 4))
                .endPage(Math.min(productList.getTotalPages(), productList.getPageable().getPageNumber() + 4))
                .build();

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("productDtoList", productDtoList);
        map.put("pageDto", productPageDto);

        return map;
    }

    // 중고 물품 상세 정보 조회 *24.01.24 jihyun
    @Override
    public ProductDto.Response findProductDetails(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        return ProductDto.Response.from(product);
    }
    // 중고 물품 게시판 조회 *24.02.19 jihyun
    @Override
    public List<ProductDto.Response> findProductList() {

        List<Product> productList = productRepository.findAllByOrderByPostingDateDesc();

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 카테고리별 중고 물품 목록 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findProductList(CategoryName categoryName) {

        List<Product> productList = productRepository.findAllByCategoryName(categoryName);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 최신 중고 물품 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findRecentProductList() {

        List<Product> productList = productRepository.findFirst8ByOrderByPostingDateDesc();

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 베스트 찜 중고 물품 조회 *24.01.24 jihyun
    @Override
    public List<Product> findBestProductList() {

        List<Product> productList = favoriteRepository.findProductsOrderedByCountDesc();

        List<Product> productIdxList = new ArrayList<>();

        for (Product product : productList) {
            productIdxList.add(product);
        }

        return productIdxList;
    }
}
