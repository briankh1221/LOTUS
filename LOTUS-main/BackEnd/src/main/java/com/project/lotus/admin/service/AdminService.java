package com.project.lotus.admin.service;

import com.project.lotus.admin.dto.QnaReplyDto;
import com.project.lotus.admin.dto.QnaReplyForm;
import com.project.lotus.auth.dto.AdminsignupForm;
import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.common.enums.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface AdminService {

    // 관리자 (마이 페이지) 정보 조회  *24.02.03 jihyun
    public SignupDto.Response findProfile (String accessToken);

    // 관리자 (마이 페이지) 정보 수정 *24.02.03 jihyun
    public void modifyProfile (String accessToken, AdminsignupForm.Request adminsignupForm, MultipartFile image) throws IOException;

    // 관리자 Q&A 답변 등록 *24.01.30 jihyun
    public void addReply(String accessToken, Long qnaIdx, QnaReplyForm.Request qnaReplyForm);

    // 관리자 Q&A 답변 수정 *24.01.30 jihyun
    public void modifyReply(Long qnaReplyIdx, QnaReplyForm.Request qnaReplyForm);

    // 관리자 Q&A 답변 삭제 *24.01.30 jihyun
    public void removeReply(Long qnaReplyIdx);

    // 관리자 중고 물품 수정 (카테고리, 거래 상태만 변경 가능)  *24.02.15 jihyun
    public void modifyProduct(Long productIdx, CategoryName categoryName, TransactionStatus transactionStatus);

    // 관리자 중고 물품 삭제 *24.02.15 jihyun
    public void removeProduct(Long productIdx);

    // 관리자 리뷰 삭제 *24.02.15 jihyun
    public void removeReivew(Long reviewIdx);

    // 관리자 Q&A 답변 조회 *24.02.21 jihyun
    public List<QnaReplyDto.Response> findQnaReplyList();
}
