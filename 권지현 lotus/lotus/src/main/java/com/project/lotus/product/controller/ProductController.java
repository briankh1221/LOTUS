package com.project.lotus.product.controller;

import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.product.service.impl.ProductServiceImpl;
import com.project.lotus.review.dto.ReviewDto;
import com.project.lotus.review.dto.ReviewForm;
import com.project.lotus.user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    private final UserServiceImpl userServiceImpl;

    // 상품 상세 정보 조회 *24.01.25 jihyun
    @GetMapping("/{productIdx}")
    public ResponseEntity<ProductDto.Response> productDetails(
            @PathVariable Long productIdx) {

        ProductDto.Response response = productServiceImpl.findProductDetails(productIdx);

        return ResponseEntity.status(OK).body(response);
    }

    // 카테고리별 상품 목록 조회 *24.01.25 jihyun
    @GetMapping("/category-list/{categoryIdx}")
    public ResponseEntity<List<ProductDto.Response>> productList(
            @PathVariable Long categoryIdx) {

        List<ProductDto.Response> productDtoList = productServiceImpl.findProductList(categoryIdx);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 최신 상품 조회 *24.01.25 jihyun
    @GetMapping("/best-list")
    public ResponseEntity<List<ProductDto.Response>> recentProductList() {

        List<ProductDto.Response> productDtoList = productServiceImpl.findRecentProductList();

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // ***베스트 상품 조회 작성 예정 *24.01.25 jihyun***

}
