package com.project.lotus.user.service;

import com.project.lotus.favorite.dto.FavoriteDto;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.review.dto.ReviewDto;
import com.project.lotus.review.dto.ReviewForm;
import com.project.lotus.user.dto.QnaDto;
import com.project.lotus.user.dto.QnaForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    // 이용자 상품 등록 *24.01.19 jihyun
    public void addProduct(List<MultipartFile> images, ProductForm.Request productForm, String token)
            throws IOException;

    // 이용자 상품 수정 *24.01.19 jihyun
    public void modifyProduct(List<MultipartFile> images, ProductForm.Request productForm, Long productIdx)
            throws IOException;

    // 이용자 상품 삭제 *24.01.19 jihyun
    public void removeProduct(Long productIdx)
            throws IOException;

    // 이용자 찜 상품 조회 *24.01.19 jihyun
    public List<FavoriteDto.Response> findFavoriteList(String token);

    // 이용자 판매 상품 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findSellingList(String token);

    // 리뷰 등록 *24.01.26 jihyun
    public void addReview(List<MultipartFile> images, ReviewForm reviewForm, Long productIdx, String token) throws IOException;

    // 리뷰 수정 *24.01.26 jihyun
    public void modifyReview(List<MultipartFile> images, ReviewForm reviewForm, Long reviewIdx) throws IOException;

    // 리뷰 삭제 *24.01.26 jihyun
    public void removeReivew(Long reviewIdx);

    // 한 상품에 대한 모든 리뷰 조회 *@4.01.24 jihyun
    public List<ReviewDto> findReviewList(Long productIdx);

    // Q&A 게시판 등록 *24.01.28 jihyun
    public void addQna(List<MultipartFile> images, QnaForm qnaForm, String token) throws IOException;

    // Q&A 게시판 수정 *24.01.28 jihyun
    public void modifyQna(List<MultipartFile> images, QnaForm qnaForm, Long qnaIdx) throws IOException;

    // Q&A 게시판 삭제 *24.01.28 jihyun
    public void removeQna(Long qnaIdx);

    // Q&A 게시판 전체 조회 *24.01.28 jihyun
    public List<QnaDto> findQnaList();

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    public List<QnaDto> findQnaList(String token);
}
