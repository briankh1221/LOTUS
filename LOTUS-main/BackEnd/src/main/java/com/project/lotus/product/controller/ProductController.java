package com.project.lotus.product.controller;

import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Product-Controller", description = "중고 물품 관련 API")
@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    // 키워드 검색 *24.02.08 jihyun
    @Operation(summary = "키워드 검색", description = "키워드 검색을 통해 중고 물품을 찾습니다.")
    @GetMapping("/product-list")
    public ResponseEntity<Map<String, Object>> productList(
            @PageableDefault(size=8) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String keyword) {

        Map<String, Object> pageMap = productService.productList(pageable, keyword);

        return ResponseEntity.status(OK).body(pageMap);
    }

    // 중고 물품 상세 정보 조회 *24.01.25 jihyun
    @Operation(summary = "중고 물품 상세 조회", description = "중고 물품을 상세 조회합니다.")
    @GetMapping("/{productIdx}")
    public ResponseEntity<ProductDto.Response> productDetails(
            @PathVariable Long productIdx) {

        ProductDto.Response response = productService.findProductDetails(productIdx);

        return ResponseEntity.status(OK).body(response);
    }

    // 카테고리 별 중고 물품 목록 조회 *24.01.25 jihyun
    @Operation(summary = "카테고리 별 중고 물품 상세 조회", description = "카테고리별로 중고 물품을 상세 조회합니다.")
    @GetMapping("/category-list/{categoryName}")
    public ResponseEntity<List<ProductDto.Response>> productList(
            @PathVariable CategoryName categoryName) {

        List<ProductDto.Response> productDtoList = productService.findProductList(categoryName);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 최근 등록한 중고 물품 조회 *24.01.25 jihyun
    @Operation(summary = "최근 등록한 중고 물품 조회", description = "최근 등록한 중고 물품을 조회합니다.")
    @GetMapping("/recent-list")
    public ResponseEntity<List<ProductDto.Response>> recentProductList() {

        List<ProductDto.Response> productDtoList = productService.findRecentProductList();

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 최다 찜 중고 물품 조회 *24.02.02 jihyun
    @Operation(summary = "최다 찜 중고 물품 조회", description = "최다 찜 중고 물품을 조회합니다.")
    @GetMapping("/best-list")
    public ResponseEntity<List<Product>> bestProductList() {

        List<Product> productIdxList = productService.findBestProductList();

        return ResponseEntity.status(OK).body(productIdxList);
    }
}
