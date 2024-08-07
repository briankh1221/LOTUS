package com.project.lotus.product.service;

import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

    // 키워드 검색 *24.02.08 jihyun
    Map<String, Object> productList(Pageable pageable, String Keyword);

    // 중고 물품 상세 조회 *24.01.24 jihyun
    public ProductDto.Response findProductDetails(Long productIdx);

    // 중고 물품 전체 조회 *24.02.19 jihyun
    public List<ProductDto.Response> findProductList();

    // 카테고리별 중고 물품 전체 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findProductList(CategoryName categoryName);

    // 최신 중고 물품 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findRecentProductList();

    // 최다 찜 중고 물품 조회 *24.01.24 jihyun
    public List<Product> findBestProductList();
}
