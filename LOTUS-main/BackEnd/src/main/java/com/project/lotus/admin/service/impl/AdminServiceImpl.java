package com.project.lotus.admin.service.impl;

import com.project.lotus.admin.dto.QnaReplyDto;
import com.project.lotus.admin.dto.QnaReplyForm;
import com.project.lotus.admin.entity.QnaReply;
import com.project.lotus.admin.repository.QnaReplyRepository;
import com.project.lotus.admin.service.AdminService;
import com.project.lotus.auth.dto.AdminsignupForm;
import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.chat.entity.Room;
import com.project.lotus.chat.repository.RoomRepository;
import com.project.lotus.common.config.security.TokenProvider;
import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.common.service.UploadService;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.repository.ProductRepository;
import com.project.lotus.user.entity.Favorite;
import com.project.lotus.user.entity.Qna;
import com.project.lotus.user.entity.Review;
import com.project.lotus.user.repository.FavoriteRepository;
import com.project.lotus.user.repository.QnaRepository;
import com.project.lotus.user.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.project.lotus.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UploadService uploadService;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;
    private final ReviewRepository reviewRepository;
    private final QnaRepository qnaRepository;
    private final QnaReplyRepository qnaReplyRepository;
    private final RoomRepository roomRepository;

    // 관리자 (마이 페이지) 정보 조회 *24.02.03 jihyun
    @Override
    public SignupDto.Response findProfile(String accessToken) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        return SignupDto.Response.from(user);
    }

    // 관리자 (마이 페이지) 정보 수정 *24.02.03 jihyun
    @Override
    public void modifyProfile(String accessToken, AdminsignupForm.Request adminSignupForm,
                              MultipartFile image) throws IOException {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        SignupDto.Request signupDto = SignupDto.Request.from(adminSignupForm);

        if (image != null && !image.isEmpty()) {
            String imagePath = uploadService.fileUpload(image);
            signupDto.setImage(imagePath);
        }

        user.modifyUser(signupDto, passwordEncoder.encode(signupDto.getPassword()));

        userRepository.save(user);
    }

    // 관리자 Q&A 답변 등록 *24.02.01 jihyun
    @Override
    public void addReply(String accessToken, Long qnaIdx, QnaReplyForm.Request qnaReplyForm) {

        String email = tokenProvider.getEmail(accessToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_EXISTS));

        QnaReplyDto.Request qnaReplyDto = QnaReplyDto.Request.from(qnaReplyForm);

        QnaReply qnaReply = qnaReplyRepository.save(QnaReply.from(qnaReplyDto, user, qnaIdx));

        Qna qna = qnaRepository.findByQnaIdx(qnaIdx)
                .orElseThrow(() -> new CustomException(QNA_NOT_EXISTS));

        qna.setQnaReply(qnaReply);

        qnaRepository.save(qna);
    }

    // 관리자 Q&A 답변 수정 *24.02.01 jihyun
    @Override
    public void modifyReply(Long qnaReplyIdx, QnaReplyForm.Request qnaReplyForm) {

        QnaReply qnaReply = qnaReplyRepository.findByQnaReplyIdx(qnaReplyIdx)
                .orElseThrow(() -> new CustomException(QNA_REPLY_NOT_EXISTS));

        QnaReplyDto.Request qnaReplyDto = QnaReplyDto.Request.from(qnaReplyForm);

        qnaReply.modifyQnaReply(qnaReplyDto);

        qnaReplyRepository.save(qnaReply);
    }

    // 관리자 Q&A 답변 삭제 *24.02.01 jihyun
    @Override
    public void removeReply(Long qnaReplyIdx) {

        QnaReply qnaReply = qnaReplyRepository.findByQnaReplyIdx(qnaReplyIdx)
                .orElseThrow(() -> new CustomException(QNA_REPLY_NOT_EXISTS));

        qnaReplyRepository.delete(qnaReply);
    }

    // 관리자 중고 물품 수정 (카테고리, 거래 상태만 변경 가능)  *24.02.15 jihyun
    @Override
    public void modifyProduct(Long productIdx, CategoryName categoryName, TransactionStatus transactionStatus) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        product.setCategoryName(categoryName);

        product.setTransactionStatus(transactionStatus);

        productRepository.save(product);
    }

    // 관리자 중고 물품 삭제 *24.02.15 jihyun
    @Override
    public void removeProduct(Long productIdx) {

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

        try {
            Favorite favorite = favoriteRepository.findByProduct(product)
                    .orElseThrow(() -> new CustomException(FAVORITE_NOT_EXISTS));

            favoriteRepository.delete(favorite);

        } catch (CustomException e) {
            log.info("중고 물품 찜이 없습니다.");
        }

        try {
            Room room = roomRepository.findByProduct(product)
                    .orElseThrow(() -> new CustomException(ROOM_LIST_NOT_EXISTS));

            roomRepository.delete(room);

        } catch (CustomException e) {
            log.info("채팅방이 없습니다.");

        } finally {
            productRepository.delete(product);
        }
    }

    // 관리자 리뷰 삭제 *24.02.15 jihyun
    @Override
    public void removeReivew(Long reviewIdx) {

        Review review = reviewRepository.findById(reviewIdx)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_EXISTS));

        reviewRepository.delete(review);
    }

    // 관리자 Q&A 답변 조회 *24.02.21 jihyun
    @Override
    public List<QnaReplyDto.Response> findQnaReplyList() {

        List<QnaReply> qnaReplyList = qnaReplyRepository.findAllByOrderByPostingDateDesc();

        List<QnaReplyDto.Response> qnaReplyDtoList = new ArrayList<>();

        for (QnaReply qnaReply : qnaReplyList) {
            qnaReplyDtoList.add(QnaReplyDto.Response.from(qnaReply));
        }

        return qnaReplyDtoList;
    }
}
