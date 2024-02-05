package com.project.lotus.user.service.impl;

import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.common.config.security.TokenProvider;
import com.project.lotus.common.service.UploadService;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.favorite.dto.FavoriteDto;
import com.project.lotus.favorite.entity.Favorite;
import com.project.lotus.favorite.repository.FavoriteRepository;
import com.project.lotus.product.dto.ProductDto;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.repository.ProductRepository;
import com.project.lotus.review.dto.ReviewDto;
import com.project.lotus.review.dto.ReviewForm;
import com.project.lotus.review.entity.Review;
import com.project.lotus.review.repository.ReviewRepository;
import com.project.lotus.user.dto.QnaDto;
import com.project.lotus.user.dto.QnaForm;
import com.project.lotus.user.entity.Qna;
import com.project.lotus.user.repository.QnaRepository;
import com.project.lotus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.lotus.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ProductRepository productRepository;

    private final FavoriteRepository favoriteRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    private final QnaRepository qnaRepository;

    private final TokenProvider tokenProvider;

    private final UploadService uploadService;

    // 상품 등록 *24.01.19 jihyun
    @Override
    public void addProduct(List<MultipartFile> images, ProductForm.Request productForm, String token)
            throws IOException {

        String email = tokenProvider.getEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        ProductDto.Request productDto = ProductDto.Request.from(productForm);

        if(images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            productDto.setImage(imagePaths);
        }

        productRepository.save(Product.from(productDto, user));
    }

    // 상품 수정 *24.01.19 jihyun
    @Override
    @Transactional
    public void modifyProduct(List<MultipartFile> images, ProductForm.Request productForm, Long productIdx)
            throws IOException {

        ProductDto.Request productDto = ProductDto.Request.from(productForm);

        if(images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            productDto.setImage(imagePaths);
        }

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        product.modifyProduct(productDto);

        productRepository.save(product);
    }

    // 상품 삭제 *24.01.19 jihyun
    @Override
    public void removeProduct(Long productIdx) {

        Product product = productRepository.findById(productIdx)
               .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

       productRepository.delete(product);
    }

    // 찜 상품 조회 *24.01.19 jihyun
    @Override
    public List<FavoriteDto.Response> findFavoriteList(String token) {

        String email = tokenProvider.getEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Favorite> favoriteList = favoriteRepository.findAllByUser(user);

        List<FavoriteDto.Response> favoriteDtoList = new ArrayList<>();

        for (Favorite favorite : favoriteList) {
            favoriteDtoList.add(FavoriteDto.Response.from(favorite));
        }

        return favoriteDtoList;
    }

    // 판매 상품 조회 *24.01.24 jihyun
    @Override
    public List<ProductDto.Response> findSellingList(String token) {

        String email = tokenProvider.getEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Product> productList = productRepository.findAllByUser(user);

        List<ProductDto.Response> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            productDtoList.add(ProductDto.Response.from(product));
        }

        return productDtoList;
    }

    // 리뷰 등록 *24.01.26 jihyun
    @Override
    public void addReview(List<MultipartFile> images, ReviewForm reviewForm, Long productIdx, String token) throws IOException {

        String email = tokenProvider.getEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        ReviewDto reviewDto = ReviewDto.from(reviewForm);

        if(images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            reviewDto.setImage(imagePaths);
        }

        reviewRepository.save(Review.from(reviewDto, user, product));
    }

    // 리뷰 수정 *24.01.26 jihyun
    @Override
    @Transactional
    public void modifyReview(List<MultipartFile> images, ReviewForm reviewForm, Long reviewIdx) throws IOException {

        ReviewDto reviewDto = ReviewDto.from(reviewForm);

        if(images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            reviewDto.setImage(imagePaths);
        }

        Review review = reviewRepository.findById(reviewIdx)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_EXISTS));

        review.modifyReview(reviewDto);

        reviewRepository.save(review);

    }

    // 리뷰 삭제 *24.01.26 jihyun
    @Override
    public void removeReivew(Long reviewIdx) {

        Review review = reviewRepository.findById(reviewIdx)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_EXISTS));

        reviewRepository.delete(review);
    }

    // 한 상품에 대한 모든 리뷰 조회 *24.01.24 jihyun
    @Override
    public List<ReviewDto> findReviewList(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        List<Review> reviewList = reviewRepository.findAllByProduct(product);

        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (Review review : reviewList) {
            reviewDtoList.add(ReviewDto.from(review));
        }

        return reviewDtoList;
    }

    // Q&A 게시판 등록 *24.01.28 jihyun
    @Override
    public void addQna(List<MultipartFile> images, QnaForm qnaForm, String token) throws IOException {

        String email = tokenProvider.getEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        QnaDto qnaDto = QnaDto.from(qnaForm);

        if(images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            qnaDto.setImage(imagePaths);
        }

        qnaRepository.save(Qna.from(qnaDto, user));
    }

    // Q&A 게시판 수정 *24.01.28 jihyun
    @Override
    public void modifyQna(List<MultipartFile> images, QnaForm qnaForm, Long qnaIdx) throws IOException {

        QnaDto qnaDto = QnaDto.from(qnaForm);

        if(images != null && !images.isEmpty()) {
            String imagePaths = uploadService.fileUpload(images);
            qnaDto.setImage(imagePaths);
        }

        Qna qna = qnaRepository.findById(qnaIdx)
                .orElseThrow(() -> new CustomException(QNA_NOT_EXISTS));

        qna.modifyQna(qnaDto);

        qnaRepository.save(qna);
    }

    // Q&A 게시판 삭제 *24.01.28 jihyun
    @Override
    public void removeQna(Long qnaIdx) {

        Qna qna = qnaRepository.findById(qnaIdx)
                .orElseThrow(() -> new CustomException(QNA_NOT_EXISTS));

        qnaRepository.delete(qna);
    }

    // Q&A 게시판 전체 조회 *24.01.28 jihyun
    @Override
    public List<QnaDto> findQnaList() {

        List<Qna> qnaList = qnaRepository.findAll();

        List<QnaDto> qnaDtoList = new ArrayList<>();

        for (Qna qna : qnaList) {
            qnaDtoList.add(QnaDto.from(qna));
        }

        return qnaDtoList;
    }

    // 이용자가 작성한 Q&A 게시판 전체 조회 *24.01.28 jihyun
    @Override
    public List<QnaDto> findQnaList(String token) {

        String email = tokenProvider.getEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        List<Qna> qnaList = qnaRepository.findAllByUser(user);

        List<QnaDto> qnaDtoList = new ArrayList<>();

        for (Qna qna : qnaList) {
            qnaDtoList.add(QnaDto.from(qna));
        }

        return qnaDtoList;
    }
}
