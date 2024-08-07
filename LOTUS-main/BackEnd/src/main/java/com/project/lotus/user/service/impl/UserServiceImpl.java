package com.project.lotus.user.service.impl;

import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.auth.dto.UsersignupForm;
import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.common.config.security.TokenProvider;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.common.service.UploadService;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.repository.ProductRepository;
import com.project.lotus.user.dto.*;
import com.project.lotus.user.entity.Favorite;
import com.project.lotus.user.entity.Qna;
import com.project.lotus.user.entity.Review;
import com.project.lotus.user.repository.FavoriteRepository;
import com.project.lotus.user.repository.QnaRepository;
import com.project.lotus.user.repository.ReviewRepository;
import com.project.lotus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.lotus.common.enums.TransactionStatus.*;
import static com.project.lotus.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UploadService uploadService;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;
    private final ReviewRepository reviewRepository;
    private final QnaRepository qnaRepository;

    // 이용자 마이 페이지 정보 조회 *24.02.03 jihyun
    @Override
    public SignupDto.Response findProfile(String accessToken) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        return SignupDto.Response.from(user);
    }

    // 이용자 마이 페이지 정보 수정 *24.02.03 jihyun
    @Override
    public void modifyProfile(String accessToken, UsersignupForm.Request usersignupForm,
                              MultipartFile image) throws IOException {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        SignupDto.Request signupDto = SignupDto.Request.from(usersignupForm);

        if (image != null && !image.isEmpty()) {
            String imagePath = uploadService.fileUpload(image);

            signupDto.setImage(imagePath);
        }

        if (signupDto.getPassword().equals(user.getPassword())) {
            user.modifyUser(signupDto, user.getPassword());

        } else {
            user.modifyUser(signupDto, passwordEncoder.encode(signupDto.getPassword()));
        }

        userRepository.save(user);
    }

    // 이용자 중고 물품 등록 *24.01.19 jihyun
    @Override
    public void addProduct(String accessToken, ProductForm.Request productForm,
                           List<MultipartFile> images) throws IOException {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        ProductDto.Request productDto = ProductDto.Request.from(productForm);

        if (images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);

            productDto.setImages(imagePaths);
        }

        productRepository.save(Product.from(productDto, user));
    }

    // 이용자 중고 물품 수정 *24.01.19 jihyun
    @Override
    public void modifyProduct(Long productIdx, ProductForm.Request productForm,
                              List<MultipartFile> images) throws IOException {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        ProductDto.Request productDto = ProductDto.Request.from(productForm);


        if (images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileModify(images, product);

            productDto.setImages(imagePaths);
        }

        product.modifyProduct(productDto);

        productRepository.save(product);
    }

    // 이용자 중고 물품 삭제 *24.01.19 jihyun
    @Override
    public void removeProduct(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        try {
            Favorite favorite = favoriteRepository.findByProduct(product)
                    .orElseThrow(() -> new CustomException(FAVORITE_NOT_EXISTS));

            favoriteRepository.delete(favorite);

        } catch (Exception e) {} finally {
            productRepository.delete(product);
        }
    }

    // 이용자 찜 등록 *24.01.31 jihyun
    @Override
    public void addfavorite(String accessToken, Long productIdx) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        favoriteRepository.save(Favorite.from(user, product));
    }

    // 이용자 찜 삭제 *24.01.31 jihyun
    @Override
    public void removeFavorite(String accessToken, Long productIdx) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        Favorite favorite = favoriteRepository.findByUserAndProduct(user,product)
                        .orElseThrow( ()-> new CustomException(FAVORITE_NOT_EXISTS));

        favoriteRepository.delete(favorite);
    }

    // 이용자 찜한 물품 조회 *24.01.19 jihyun
    @Override
    public List<FavoriteDto.Response> findFavoriteList(String accessToken) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Favorite> favoriteList = favoriteRepository.findAllByUser(user);

        List<FavoriteDto.Response> favoriteDtoList = new ArrayList<>();

        for (Favorite favorite : favoriteList) {
            favoriteDtoList.add(FavoriteDto.Response.from(favorite));
        }

        return favoriteDtoList;
    }

    // 이용자 중고 물품 예약 *24.02.08 jihyun
    @Override
    public void bookProduct(String accessToken, Long productIdx) {

        String email = tokenProvider.getEmail(accessToken);

        User buyer = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        product.setBuyer(buyer);

        product.setTransactionStatus(UNDER_RESERVATION);

        productRepository.save(product);
    }

    // 이용자 중고 물품 예약 취소 *24.02.08 jihyun
    @Override
    public void bookCancel(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        product.setBuyer(null);

        product.setTransactionStatus(ON_SALE);

        productRepository.save(product);
    }

    // 이용자(판매자 일 경우) 판매 종료 *24.02.08 jihyun
    @Override
    public void soldProduct(Long productIdx) {

        Product product = productRepository.findById(productIdx)
            .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        product.setTransactionStatus(COMPLETED);

        productRepository.save(product);
    }

    // 이용자(판매자 일 경우) 판매 종료 취소 *24.02.08 jihyun
    @Override
    public void saleProduct(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        product.setTransactionStatus(ON_SALE);

        productRepository.save(product);
    }

    // 이용자(판매자 일 경우) 모든 판매 중고 물품 조회 *24.02.08 jihyun
    @Override
    public List<ProductDto.Response> findProductList(Long idx) {

        User user = userRepository.findByIdx(idx)
                .orElseThrow(()-> new CustomException(USER_NOT_EXISTS));

        List<Product> productList = productRepository.findAllByUser(user);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 이용자(판매자 일 경우) 모든 판매 리뷰 조회 *24.02.08 jihyun
    @Override
    public List<ReviewDto.Response> findAllReviews(Long idx) {

            List<ReviewDto.Response> reviewDtoList = new ArrayList<>();

            try {
                List<Review> reviewList = reviewRepository.findByProduct_User_IdxOrUser_Idx(idx, idx)
                    .orElseThrow(()->new CustomException(REVIEW_NOT_EXISTS));

                for (Review review: reviewList) {
                    reviewDtoList.add(ReviewDto.Response.from(review));
                }

            } catch (Exception e) {}

        return reviewDtoList;
    }

    // 이용자(구매자일 경우) 모든 리뷰 조회 *24.02.08 jihyun
    @Override
    public List<ReviewDto.Response> findAllReviews(String accessToken) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<ReviewDto.Response> reviewDtoList = new ArrayList<>();

        try {
            List<Review> reviewList = reviewRepository.findByProduct_User_IdxOrUser_Idx(user.getIdx(), user.getIdx())
                    .orElseThrow(()->new CustomException(REVIEW_NOT_EXISTS));

            for (Review review: reviewList) {
                reviewDtoList.add(ReviewDto.Response.from(review));
            }

        } catch (Exception e) {}

        return reviewDtoList;
    }

    // 이용자 판매하는 중고 물품 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findSellingAllList(String accessToken) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Product> productList = productRepository.findAllByUser(user);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 거래 상태에 따른 이용자 중고 물품 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findSellingList(String accessToken, TransactionStatus transactionStatus) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Product> productList = productRepository.findAllByUserAndTransactionStatus(user, transactionStatus);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 이용자(구매자 일 경우) 거래 상태 (예약 중, 거래 완료)에 따른 중고 물품 조회 *24.02.05 jihyun
    @Override
    public List<ProductDto.Response> buyingList(String accessToken, TransactionStatus transactionStatus) {

        String email = tokenProvider.getEmail(accessToken);

        User buyer = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Product> productList = productRepository.findAllByBuyer_idxAndTransactionStatus(buyer.getIdx(), transactionStatus);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 이용자 리뷰 등록 *24.01.26 jihyun
    @Override
    public void addReview(String accessToken, Long productIdx, ReviewForm.Request reviewForm,
                          List<MultipartFile> images) throws IOException {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        ReviewDto.Request reviewDto = ReviewDto.Request.from(reviewForm);

        if (images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);

            reviewDto.setImages(imagePaths);
        }

        reviewRepository.save(Review.from(reviewDto, user, product));
    }

    // 이용자 리뷰 수정 *24.01.26 jihyun
    @Override
    public void modifyReview(Long reviewIdx, ReviewForm.Request reviewForm,
                             List<MultipartFile> images) throws IOException {

        Review review = reviewRepository.findById(reviewIdx)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_EXISTS));

        ReviewDto.Request reviewDto = ReviewDto.Request.from(reviewForm);

        if (images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileModify(images, review);

            reviewDto.setImages(imagePaths);
        }

        review.modifyReview(reviewDto);

        reviewRepository.save(review);
    }

    // 이용자 리뷰 삭제 *24.01.26 jihyun
    @Override
    public void removeReivew(Long reviewIdx) {

        Review review = reviewRepository.findById(reviewIdx)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_EXISTS));

        reviewRepository.delete(review);
    }

    // 이용자 한 물품에 대한 모든 리뷰 조회 *24.01.24 jihyun
    @Override
    public List<ReviewDto.Response> findReviewList(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        List<Review> reviewList = reviewRepository.findAllByProduct(product);

        List<ReviewDto.Response> reviewDtoList = new ArrayList<>();

        for (Review review : reviewList) {
            reviewDtoList.add(ReviewDto.Response.from(review));
        }

        return reviewDtoList;
    }

    // 리뷰 게시판 조회 *24.02.19 jihyun
    public Map<String, Object> findReviewList(Pageable pageable, String Keyword) {

        Page<Review> reviewList = reviewRepository.findByTitleContainingOrContentContaining(Keyword, Keyword, pageable);

        ReviewPageDto reviewPageDto = ReviewPageDto.builder()
                .startPage(Math.max(0, reviewList.getPageable().getPageNumber() - 4))
                .endPage(Math.min(reviewList.getTotalPages(), reviewList.getPageable().getPageNumber() + 4))
                .build();

        List<ReviewDto.Response> reviewDtoList = new ArrayList<>();

        for (Review review : reviewList) {
            reviewDtoList.add(ReviewDto.Response.from(review));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("reviewDtoList", reviewDtoList);
        map.put("reviewPageDto", reviewPageDto);

        return map;
    }

    // 이용자 Q&A 게시판 등록 *24.01.28 jihyun
    @Override
    public void addQna(String accessToken, QnaForm.Request qnaForm, List<MultipartFile> images) throws IOException {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        QnaDto.Request qnaDto = QnaDto.Request.from(qnaForm);

        if (images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            qnaDto.setImages(imagePaths);
        }

        qnaRepository.save(Qna.from(qnaDto, user));
    }

    // 이용자 Q&A 게시판 수정 *24.01.28 jihyun
    @Override
    public void modifyQna(Long qnaIdx, QnaForm.Request qnaForm, List<MultipartFile> images) throws IOException {

        Qna qna = qnaRepository.findByQnaIdx(qnaIdx)
                .orElseThrow(() -> new CustomException(QNA_NOT_EXISTS));

        QnaDto.Request qnaDto = QnaDto.Request.from(qnaForm);

        if (images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileModify(images, qna);
            qnaDto.setImages(imagePaths);
        }

        qna.modifyQna(qnaDto);

        qnaRepository.save(qna);
    }

    // 이용자 Q&A 게시판 삭제 *24.01.28 jihyun
    @Override
    public void removeQna(Long qnaIdx) {

        Qna qna = qnaRepository.findById(qnaIdx)
                .orElseThrow(() -> new CustomException(QNA_NOT_EXISTS));

        qnaRepository.delete(qna);
    }

    // 이용자 Q&A 게시판 전체 조회 *24.01.28 jihyun
    @Override
    public List<QnaDto.Response> findQnaList() {

        List<Qna> qnaList = qnaRepository.findAll();

        List<QnaDto.Response> qnaDtoList = new ArrayList<>();

        for (Qna qna : qnaList) {
            qnaDtoList.add(QnaDto.Response.from(qna));
        }

        return qnaDtoList;
    }

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    @Override
    public List<QnaDto.Response> findQnaList(String accessToken) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Qna> qnaList = qnaRepository.findAllByUser(user);

        List<QnaDto.Response> qnaDtoList = new ArrayList<>();

        for (Qna qna : qnaList) {
            qnaDtoList.add(QnaDto.Response.from(qna));
        }

        return qnaDtoList;
    }

    // 관리자 Q&A 게시판 전체 조회 *24.02.20 jihyun
    @Override
    public Map<String, Object> findQnaList(Pageable pageable, String Keyword) {

        Page<Qna> qnaList = qnaRepository.findByTitleContainingOrContentContaining(Keyword, Keyword, pageable);

        QnaPageDto qnaPageDto = QnaPageDto.builder()
                .startPage(Math.max(0, qnaList.getPageable().getPageNumber() - 4))
                .endPage(Math.min(qnaList.getTotalPages(), qnaList.getPageable().getPageNumber() + 4))
                .build();

        List<QnaDto.Response> qnaDtoList = new ArrayList<>();

        for (Qna qna : qnaList) {
            qnaDtoList.add(QnaDto.Response.from(qna));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("qnaDtoList", qnaDtoList);
        map.put("qnaPageDto", qnaPageDto);

        return map;
    }
}
