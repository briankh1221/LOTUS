package com.project.lotus.product.service;

import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {

    // 상품 상세 조회 *24.01.24 jihyun
    public ProductDto.Response findProductDetails(Long productIdx);

    // 상품 전체 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findProductList(Long categoryIdx);

    // 최신 상품 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findRecentProductList();

    // 최다 찜 상품 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findBestProductList();


}
