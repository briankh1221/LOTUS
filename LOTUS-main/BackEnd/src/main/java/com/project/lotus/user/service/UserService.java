package com.project.lotus.user.service;

import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.auth.dto.UsersignupForm;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.user.dto.FavoriteDto;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.user.dto.ReviewDto;
import com.project.lotus.user.dto.ReviewForm;
import com.project.lotus.user.dto.QnaDto;
import com.project.lotus.user.dto.QnaForm;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {

    // 이용자 마이 페이지 정보 조회 *24.02.03 jihyun
    public SignupDto.Response findProfile (String accessToken);

    // 이용자 마이 페이지)정보 수정 *24.02.03 jihyun
    public void modifyProfile (String AccessToken, UsersignupForm.Request usersignupForm, MultipartFile image) throws IOException;

    // 이용자 중고 물품 등록 *24.01.19 jihyun
    public void addProduct(String accessToken, ProductForm.Request productForm, List<MultipartFile> images)
            throws IOException;

    // 이용자 중고 물품 수정 *24.01.19 jihyun
    public void modifyProduct(Long productIdx, ProductForm.Request productForm, List<MultipartFile> images)
            throws IOException;

    // 이용자 중고 물품 삭제 *24.01.19 jihyun
    public void removeProduct(Long productIdx)
            throws IOException;

    // 이용자 찜 등록 *24.01.31 jihyun
    public void addfavorite(String accessToken, Long productIdx);

    // 이용자 찜 삭제 *24.01.31 jihyun
    public void removeFavorite(String accessToken, Long productIdx);

    // 이용자 찜한 물품 조회 *24.01.19 jihyun
    public List<FavoriteDto.Response> findFavoriteList(String accessToken);

    // 이용자 중고 물품 예약 *24.02.08 jihyun
    public void bookProduct(String accessToken, Long productIdx);

    // 이용자 중고 물품 예약 취소 *24.02.08 jihyun
    public void bookCancel(Long productIdx);

    // 이용자(판매자 일 경우) 판매 종료 *24.02.08 jihyun
    public void soldProduct(Long productIdx);

    // 이용자(판매자 일 경우) 판매 종료 취소 *24.02.08 jihyun
    public void saleProduct(Long productIdx);

    // 이용자(판매자 일 경우) 모든 판매 중고 물품 조회 *24.02.08 jihyun
    public List<ProductDto.Response> findProductList(Long idx);

    // 이용자(판매자 일 경우) 모든 판매 리뷰 조회 *24.02.08 jihyun
    public List<ReviewDto.Response> findAllReviews(Long idx);

    // 이용자(구매자일 경우) 모든 리뷰 조회 *24.02.08 jihyun
    public List<ReviewDto.Response> findAllReviews(String accessToken);

    // 이용자(판매자 일 경우) 이용자 판매 중고 물품 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findSellingAllList(String accessToken);

    // 거래 상태에 따른 이용자 판매 중고 물품 조회 *24.01.24 jihyun
    public List<ProductDto.Response> findSellingList(String accessToken, TransactionStatus transactionStatus);

    // 이용자(구매자 일 경우) 거래 상태 (예약 중, 거래 완료)에 따른 상품 조회 *24.02.05 jihyun
    public List<ProductDto.Response> buyingList(String accessToken, TransactionStatus transactionStatus);

    // 이용자 리뷰 등록 *24.01.26 jihyun
    public void addReview(String accessToken, Long productIdx, ReviewForm.Request reviewForm, List<MultipartFile> images) throws IOException;

    // 이용자 리뷰 수정 *24.01.26 jihyun
    public void modifyReview(Long reviewIdx, ReviewForm.Request reviewForm, List<MultipartFile> images) throws IOException;

    // 이용자 리뷰 삭제 *24.01.26 jihyun
    public void removeReivew(Long reviewIdx);

    // 이용자 한 상품에 대한 모든 리뷰 조회 *24.01.24 jihyun
    public List<ReviewDto.Response> findReviewList(Long productIdx);

    // 리뷰 게시판 조회 *24.02.19 jihyun
    public Map<String, Object> findReviewList(Pageable pageable, String Keyword);

    // 이용자 Q&A 게시판 등록 *24.01.28 jihyun
    public void addQna(String accessToken, QnaForm.Request qnaForm, List<MultipartFile> images) throws IOException;

    // 이용자 Q&A 게시판 수정 *24.01.28 jihyun
    public void modifyQna(Long qnaIdx, QnaForm.Request qnaForm, List<MultipartFile> images) throws IOException;

    // 이용자 Q&A 게시판 삭제 *24.01.28 jihyun
    public void removeQna(Long qnaIdx);

    // 이용자 Q&A 게시판 전체 조회 *24.01.28 jihyun
    public List<QnaDto.Response> findQnaList();

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    public List<QnaDto.Response> findQnaList(String accessToken);

    // 관리자 Q&A 게시판 전체 조회 *24.02.20 jihyun
    public Map<String, Object> findQnaList(Pageable pageable, String Keyword);
}
