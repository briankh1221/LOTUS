package com.project.lotus.user.controller;

import com.project.lotus.favorite.dto.FavoriteDto;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.review.dto.ReviewDto;
import com.project.lotus.review.dto.ReviewForm;
import com.project.lotus.user.dto.QnaDto;
import com.project.lotus.user.dto.QnaForm;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    // 상품 등록 *24.01.25 jihyun
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> productAdd(
            @RequestParam(value = "image") ArrayList<MultipartFile> images,
            @Valid ProductForm.Request productForm,
            @RequestHeader(name = "Authorization") String token) throws IOException {

        userServiceImpl.addProduct(images, productForm, token);

        return ResponseEntity.status(CREATED).build();
    }

    // 상품 수정 *24.01.25 jihyun
    @PutMapping(value = "/{productIdx}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> productModify(
            @RequestParam(value = "image") ArrayList<MultipartFile> images,
            @Valid ProductForm.Request productForm,
            @PathVariable Long productIdx) throws IOException {

        userServiceImpl.modifyProduct(images, productForm, productIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 상품 삭제 *24.01.25 jihyun
    @DeleteMapping("/{productIdx}")
    public ResponseEntity<Void> productRemove(
            @PathVariable Long productIdx
    ) {

        userServiceImpl.removeProduct(productIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자 찜 상품 조회 *24.01.25 jihyun
    @GetMapping("/favorite")
    public ResponseEntity<List<FavoriteDto.Response>> favoriteList(
            @RequestHeader(name = "Authorization") String token) {

        List<FavoriteDto.Response> productDtoList = userServiceImpl.findFavoriteList(token);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 이용자 판매 상품 조회 *24.01.24 jihyun
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> sellingList(
            @RequestHeader(name = "Authorization") String token) {

        List<ProductDto.Response> productDtoList = userServiceImpl.findSellingList(token);

        return ResponseEntity.status(OK).body(productDtoList);
    }

    // 리뷰 등록 *24.01.26 jihyun
    @PostMapping(value = "/product/{productIdx}/review", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> reviewAdd(
            @RequestParam(value = "image") ArrayList<MultipartFile> images,
            @Valid ReviewForm reviewForm,
            @PathVariable Long productIdx,
            @RequestHeader(name = "Authorization") String token) throws IOException {

        userServiceImpl.addReview(images, reviewForm, productIdx, token);

        return ResponseEntity.status(CREATED).build();
    }

    // 리뷰 수정 *24.01.27 jihyun
    @PutMapping(value = "/product/{productIdx}/review?reviewIdx={reviewIdx}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> reviewModify(
            @RequestParam(value = "image") ArrayList<MultipartFile> images,
            @Valid ReviewForm reviewForm,
            @PathVariable Long reviewIdx) throws IOException {

        userServiceImpl.modifyReview(images, reviewForm, reviewIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 리뷰 삭제 *24.01.27 jihyun
    @DeleteMapping(value = "/product/{productIdx}/review?reviewIdx={reviewIdx}")
    public ResponseEntity<Void> reviewRemove(
            @PathVariable Long reviewIdx) {

        userServiceImpl.removeReivew(reviewIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 한 상품에 대한 모든 리뷰 조회 *24.01.24 jihyun
    @GetMapping(value = "/product/{productIdx}/review")
    public ResponseEntity<List<ReviewDto>> reviewList(
            @PathVariable Long productIdx) {

        List<ReviewDto> reviewDtoList = userServiceImpl.findReviewList(productIdx);

        return ResponseEntity.status(OK).body(reviewDtoList);
    }

    // Q&A 게시판 등록 *24.01.28 jihyun
    @PostMapping(value = "/qna", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> qnaAdd(
            @RequestParam(value = "image") ArrayList<MultipartFile> images,
            @Valid QnaForm qnaForm,
            @RequestHeader(name = "Authorization") String token) throws IOException {

        userServiceImpl.addQna(images, qnaForm, token);

        return ResponseEntity.status(CREATED).build();
    }

    // Q&A 게시판 수정 *24.01.28 jihyun
    @PutMapping(value = "/qna/{qnaIdx}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> qnaModify(
            @RequestParam(value = "image") ArrayList<MultipartFile> images,
            @Valid QnaForm qnaForm,
            @PathVariable Long qnaIdx) throws IOException {

        userServiceImpl.modifyQna(images, qnaForm, qnaIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // Q&A 게시판 삭제 *24.01.28 jihyun
    @DeleteMapping(value = "/qna/{qnaIdx}")
    public ResponseEntity<Void> qnaRemove(
            @PathVariable Long qnaIdx) {

        userServiceImpl.removeQna(qnaIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    @GetMapping("/qna")
    public ResponseEntity<List<QnaDto>> qnaList() {

        List<QnaDto> qnatDtoList = userServiceImpl.findQnaList();

        return ResponseEntity.status(OK).body(qnatDtoList);
    }

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    public ResponseEntity<List<QnaDto>> qnaList(
            @RequestHeader(name = "Authorization") String token) {

        List<QnaDto> qnatDtoList = userServiceImpl.findQnaList(token);

        return ResponseEntity.status(OK).body(qnatDtoList);
    }
}
