package com.project.lotus.user.controller;

import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.auth.dto.UsersignupForm;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.user.dto.*;
import com.project.lotus.user.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "User-Controller", description = "이용자 관련 API")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    // 이용자 마이 페이지 정보 조회 *24.02.03 jihyun
    @Operation(summary = "이용자 마이 페이지 조회", description = "이용자가 마이 페이지를 조회합니다.")
    @GetMapping(value = "/details")
    public ResponseEntity<SignupDto.Response> profileFind (
             @RequestHeader(name = "Authorization") String accessToken) {

        SignupDto.Response signupDto = userService.findProfile(accessToken);

        return ResponseEntity.status(OK).body(signupDto);
    }

    // 이용자 마이 페이지 정보 수정 *24.02.03 jihyun
    @Operation(summary = "이용자 마이 페이지 수정", description = "이용자가 마이 페이지를 수정합니다.")
    @PutMapping(value = "/details", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> profileModify (
            @RequestHeader(name = "Authorization") String accessToken,
            @Valid UsersignupForm.Request usersignupForm,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        userService.modifyProfile(accessToken, usersignupForm, image);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 중고 물품 등록 *24.01.25 jihyun
    @Operation(summary = "이용자 중고 물품 등록", description = "이용자가 중고 물품 등록합니다.")
    @PostMapping(value = "/product", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> productAdd (
            @RequestHeader(name = "Authorization") String accessToken,
            @Valid ProductForm.Request productForm,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        userService.addProduct(accessToken, productForm, images);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자 중고 물품 수정 *24.01.25 jihyun
    @Operation(summary = "이용자 중고 물품 수정", description = "이용자가 중고 물품 수정합니다.")
    @PutMapping(value = "/product/{productIdx}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> productModify (
            @PathVariable Long productIdx,
            @Valid ProductForm.Request productForm,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        userService.modifyProduct(productIdx, productForm, images);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 중고 물품 삭제 *24.01.25 jihyun
    @Operation(summary = "이용자 중고 물품 삭제", description = "이용자가 중고 물품 삭제합니다.")
    @DeleteMapping(value ="/product/{productIdx}")
    public ResponseEntity<Void> productRemove (
            @PathVariable Long productIdx) {

        userService.removeProduct(productIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 찜 등록 *24.01.31 jihyun
    @Operation(summary = "이용자 찜한 중고 물품 등록", description = "이용자가 찜한 중고 물품 등록합니다.")
    @PostMapping(value ="/product/{productIdx}/favorite")
    public ResponseEntity<Void> favoriteAdd (
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable Long productIdx) {

        userService.addfavorite(accessToken, productIdx);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자 찜 삭제 *24.01.31 jihyun
    @Operation(summary = "이용자 찜한 중고 물품 삭제", description = "이용자가 찜한 중고 물품 삭제합니다.")
    @DeleteMapping(value ="/product/{productIdx}/favorite")
    public ResponseEntity<Void> favoriteRemove (
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable Long productIdx) {

        userService.removeFavorite(accessToken, productIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 찜한 물품 조회 *24.01.25 jihyun
    @Operation(summary = "이용자 찜한 중고 물품 조회", description = "이용자가 찜한 중고 물품 조회합니다.")
    @GetMapping(value ="/product/favorite")
    public ResponseEntity<List<FavoriteDto.Response>> favoriteList (
            @RequestHeader(name = "Authorization") String accessToken) {

        List<FavoriteDto.Response> productDtoList = userService.findFavoriteList(accessToken);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 이용자 중고 물품 예약 *24.02.08 jihyun
    @Operation(summary = "이용자 중고 물품 예약", description = "이용자가 중고 물품 예약합니다.")
    @PatchMapping(value = "/product/{productIdx}/bookingstatus")
    public ResponseEntity<Void> productBook(
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable Long productIdx) {

        userService.bookProduct(accessToken, productIdx);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자 중고 물품 예약 취소 *24.02.08 jihyun
    @Operation(summary = "이용자 중고 물품 예약 취소", description = "이용자가 중고 물품 예약 취소합니다.")
    @PatchMapping(value = "/product/{productIdx}/cancelstatus")
    public ResponseEntity<Void> productCancel(
            @PathVariable Long productIdx) {

        userService.bookCancel(productIdx);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자(판매자 일 경우) 판매 종료 *24.02.08 jihyun
    @Operation(summary = "이용자 중고 물품 판매 종료", description = "이용자가 중고 물품 판매 종료 시킵니다.")
    @PatchMapping(value = "/product/{productIdx}/soldstatus")
    public ResponseEntity<Void> productSold(
            @PathVariable Long productIdx) {

        userService.soldProduct(productIdx);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자(판매자 일 경우) 판매 종료 취소 *24.02.08 jihyun
    @Operation(summary = "이용자 중고 물품 판매 종료 취소", description = "이용자가 중고 물품 판매 종료 취소 시킵니다.")
    @PatchMapping(value = "/product/{productIdx}/salestatus")
    public ResponseEntity<Void> productSale(
            @PathVariable Long productIdx) {

        userService.saleProduct(productIdx);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자(판매자 일 경우) 모든 판매 중고 물품 조회 *24.02.08 jihyun
    @Operation(summary = "이용자 판매 물품 조회", description = "판매자의 판매 중고 물품을 조회합니다.")
    @GetMapping(value = "/product-list")
    public ResponseEntity<List<ProductDto.Response>> ProductList(
            @RequestParam Long idx) {

        List<ProductDto.Response> productDtoList = userService.findProductList(idx);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 이용자(판매자 일 경우) 판매자의 모든 리뷰 조회 *24.02.08 jihyun
    @Operation(summary = "이용자가 판매자 모든 리뷰 조회", description = "이용자가 판매자 모든 리뷰를 조회합니다.")
    @GetMapping(value = "/seller-review-list")
    public ResponseEntity<List<ReviewDto.Response>> ReviewList(
            @RequestParam Long idx) {

        List<ReviewDto.Response> reviewDtoList = userService.findAllReviews(idx);

        return ResponseEntity.status(OK).body(reviewDtoList);
    }

    // 이용자(구매자일 경우) 모든 리뷰 조회 *24.02.08 jihyun
    @Operation(summary = "이용자 모든 리뷰 조회", description = "이용자 모든 리뷰를 조회합니다.")
    @GetMapping(value = "/buyer-review-list")
    public ResponseEntity<List<ReviewDto.Response>> ReviewList(
            @RequestHeader(name = "Authorization") String accessToken) {

        List<ReviewDto.Response> reviewDtoList = userService.findAllReviews(accessToken);

        return ResponseEntity.status(OK).body(reviewDtoList);
    }

    // 이용자 판매하는 중고 물품 조회 *24.01.24 jihyun
    @Operation(summary = "이용자 판매하는 중고 물품 조회", description = "이용자가 판매하는 중고 물품 조회합니다.")
    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductDto.Response>> sellingAllList (
            @RequestHeader(name = "Authorization") String accessToken) {

        List<ProductDto.Response> productDtoList = userService.findSellingAllList(accessToken);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 거래 상태에 따른 이용자 중고 물품 조회 *24.01.24 jihyun
    @Operation(summary = "이용자 거래 상태에 따른 중고 물품 조회", description = "이용자가 거래 상태에 따른 중고 물품 조회합니다.")
    @GetMapping(value = "/product/{transactionStatus}")
    public ResponseEntity<List<ProductDto.Response>> sellingList (
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable TransactionStatus transactionStatus) {

        List<ProductDto.Response> productDtoList = userService.findSellingList(accessToken, transactionStatus);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 이용자(구매자 일 경우) 거래 상태 (예약 중, 거래 완료)에 따른 중고 물품 조회 *24.02.05 jihyun
    @Operation(summary = "이용자(구매자) 거래 상태에 따른 중고 물품 조회", description = "이용자(구매자)가 거래 상태에 따른 중고 물품 조회합니다.")
    @GetMapping(value = "/buying-list/{transactionStatus}")
    public ResponseEntity<List<ProductDto.Response>> buyingList (
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable TransactionStatus transactionStatus) {

        List<ProductDto.Response> productDtoList = userService.buyingList(accessToken, transactionStatus);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 이용자 리뷰 등록 *24.01.26 jihyun
    @Operation(summary = "이용자 리뷰 등록", description = "이용자가 리뷰 등록합니다.")
    @PostMapping(value = "/product/{productIdx}/review", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> reviewAdd (
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable Long productIdx,
            @Valid ReviewForm.Request reviewForm,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        userService.addReview(accessToken, productIdx, reviewForm, images);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자 리뷰 수정 *24.01.27 jihyun
    @Operation(summary = "이용자 리뷰 수정", description = "이용자가 리뷰 수정합니다.")
    @PutMapping(value = "/product/{productIdx}/review/{reviewIdx}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> reviewModify (
            @PathVariable Long reviewIdx,
            @Valid ReviewForm.Request reviewForm,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        userService.modifyReview(reviewIdx, reviewForm, images);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 리뷰 삭제 *24.01.27 jihyun
    @Operation(summary = "이용자 리뷰 삭제", description = "이용자가 리뷰 삭제합니다.")
    @DeleteMapping(value = "/product/{productIdx}/review/{reviewIdx}")
    public ResponseEntity<Void> reviewRemove (
            @PathVariable Long reviewIdx) {

        userService.removeReivew(reviewIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 한 중고 물품에 대한 모든 리뷰 조회 *24.01.24 jihyun
    @Operation(summary = "이용자 한 중고 물품에 대한 리뷰 조회", description = "이용자가 한 중고 물품에 대한 리뷰 조회합니다.")
    @GetMapping(value = "/product/{productIdx}/review")
    public ResponseEntity<List<ReviewDto.Response>> reviewList (
            @PathVariable Long productIdx) {

        List<ReviewDto.Response> reviewDtoList = userService.findReviewList(productIdx);

        return ResponseEntity.status(OK).body(reviewDtoList);
    }

    // 이용자 Q&A 게시판 등록 *24.01.28 jihyun
    @Operation(summary = "이용자 Q&A 게시판 등록", description = "이용자가 Q&A 게시판 등록합니다.")
    @PostMapping(value = "/qna", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> qnaAdd (
            @RequestHeader(name = "Authorization") String accessToken,
            @Valid QnaForm.Request qnaForm,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        userService.addQna(accessToken, qnaForm, images);

        return ResponseEntity.status(CREATED).build();
    }

    // 이용자 Q&A 게시판 수정 *24.01.28 jihyun
    @Operation(summary = "이용자 Q&A 게시판 수정", description = "이용자가 Q&A 게시판 수정합니다.")
    @PutMapping(value = "/qna/{qnaIdx}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> qnaModify (
            @PathVariable Long qnaIdx,
            @Valid QnaForm.Request qnaForm,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        userService.modifyQna(qnaIdx, qnaForm, images);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 Q&A 게시판 삭제 *24.01.28 jihyun
    @Operation(summary = "이용자 Q&A 게시판 삭제", description = "이용자가 Q&A 게시판 삭제합니다.")
    @DeleteMapping(value = "/qna/{qnaIdx}")
    public ResponseEntity<Void> qnaRemove (
            @PathVariable Long qnaIdx) {

        userService.removeQna(qnaIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 Q&A 게시판 전체 조회 *24.01.28 jihyun
    @Operation(summary = "이용자 전체 Q&A 게시판 조회", description = "이용자가 전체 Q&A 게시판 조회합니다.")
    @GetMapping("/qna-list")
    public ResponseEntity<List<QnaDto.Response>> qnaList () {

        List<QnaDto.Response> qnatDtoList = userService.findQnaList();

        return ResponseEntity.status(OK).body(qnatDtoList);
    }

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    @Operation(summary = "이용자 작성한 Q&A 게시판 조회", description = "이용자가 작성한 Q&A 게시판 조회합니다.")
    @GetMapping("/qna")
    public ResponseEntity<List<QnaDto.Response>> qnaList (
            @RequestHeader(name = "Authorization") String accessToken) {

        List<QnaDto.Response> qnatDtoList = userService.findQnaList(accessToken);

        return ResponseEntity.status(OK).body(qnatDtoList);
    }
}
