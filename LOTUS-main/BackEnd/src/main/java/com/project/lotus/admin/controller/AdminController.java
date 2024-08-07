package com.project.lotus.admin.controller;

import com.project.lotus.admin.dto.QnaReplyDto;
import com.project.lotus.admin.dto.QnaReplyForm;
import com.project.lotus.admin.service.impl.AdminServiceImpl;
import com.project.lotus.auth.dto.AdminsignupForm;
import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.product.service.impl.ProductServiceImpl;
import com.project.lotus.user.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static org.springframework.http.HttpStatus.*;

@Tag(name = "Admin-Controller", description = "관리자 관련 API")
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminServiceImpl adminService;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;

    // 관리자 중고 물품 게시판 조회 *24.02.19 jihyun
    @Operation(summary = "관리자, 중고 물품 게시판 조회", description = "관리자가 중고 물품 게시판을 조회합니다.")
    @GetMapping("/product-list")
    public ResponseEntity<Map<String, Object>> productList (
            @PageableDefault(size=8) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String keyword) {

        // 정렬 정보 추가 (시간 역순으로 정렬) *24.02.20 jihyun
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("postingDate").descending());

        Map<String, Object> pageMap = productService.productList(pageable, keyword);

        return ResponseEntity.status(OK).body(pageMap);
    }

    // 관리자 리뷰 게시판 조회 *24.02.19 jihyun
    @Operation(summary = "관리자, 리뷰 게시판 조회", description = "관리자가 리뷰 게시판을 조회합니다.")
    @GetMapping("/review-list")
    public ResponseEntity<Map<String, Object>> reviewList (
            @PageableDefault(size=8) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String keyword) {

        // 정렬 정보 추가 (시간 역순으로 정렬) *24.02.20 jihyun
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("postingDate").descending());

        Map<String, Object> pageMap = userService.findReviewList(pageable, keyword);

        return ResponseEntity.status(OK).body(pageMap);
    }

    // 관리자 Q&A 게시판 조회 *24.02.19 jihyun
    @Operation(summary = "관리자, Q&A 게시판 조회", description = "관리자가 Q&A 게시판을 조회합니다.")
    @GetMapping("/qna-list")
    public ResponseEntity<Map<String, Object>> qnaList (
            @PageableDefault(size=8) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String keyword) {

        // 정렬 정보 추가 (시간 역순으로 정렬) *24.02.20 jihyun
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("postingDate").descending());

        Map<String, Object> pageMap = userService.findQnaList(pageable, keyword);

        return ResponseEntity.status(OK).body(pageMap);
    }

    // 관리자 Q&A 답변 조회 *24.02.21 jihyun
    @Operation(summary = "관리자, Q&A 답변 조회", description = "관리자가 Q&A 답변을 조회합니다.")
    @GetMapping("/qnareply-list")
    public ResponseEntity<List<QnaReplyDto.Response>> qnaReplyList () {

        List<QnaReplyDto.Response> qnaReplyList = adminService.findQnaReplyList();

        return ResponseEntity.status(OK).body(qnaReplyList);
    }

    // 관리자 (마이 페이지) 정보 조회 *24.02.03 jihyun
    @Operation(summary = "관리자, 나의 정보 관리 조회", description = "나의 정보 관리 조회합니다.")
    @GetMapping("/details")
    public ResponseEntity<SignupDto.Response> profileFind (
            @RequestHeader(name = "Authorization") String accessToken) {

        SignupDto.Response signupDto = adminService.findProfile(accessToken);

        return ResponseEntity.status(OK).body(signupDto);
    }

    // 관리자 (마이 페이지) 정보 수정 *24.02.03 jihyun
    @Operation(summary = "관리자, 나의 정보 관리 수정", description = "나의 정보 관리 수정합니다.")
    @PutMapping("/details")
    public ResponseEntity<Void> profileModify (
            @RequestHeader(name = "Authorization") String accessToken,
            @Valid AdminsignupForm.Request adminSignupForm,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        adminService.modifyProfile(accessToken, adminSignupForm, image);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 관리자 답변 등록 *24.02.01 jihyun
    @Operation(summary = "관리자 Q&A 답변 등록", description = "관리자가 Q&A 답변 등록합니다.")
    @PostMapping(value = "/qna/{qnaIdx}/qnareply")
    public ResponseEntity<Void> replyAdd(
            @RequestHeader(name = "Authorization") String accessToken,
            @PathVariable Long qnaIdx,
            @Valid QnaReplyForm.Request qnaReplyForm) {

        adminService.addReply(accessToken, qnaIdx, qnaReplyForm);

        return ResponseEntity.status(CREATED).build();
    }

    // 관리자 답변 수정 *24.02.01 jihyun
    @Operation(summary = "관리자 Q&A 답변 수정", description = "관리자가 Q&A 답변 수정합니다.")
    @PutMapping(value = "/qna/{qnaIdx}/qnareply/{qnaReplyIdx}")
    public ResponseEntity<Void> replyModify(
            @PathVariable Long qnaReplyIdx,
            @Valid QnaReplyForm.Request qnaReplyForm) {

        adminService.modifyReply(qnaReplyIdx, qnaReplyForm);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 관리자 답변 삭제 *24.02.01 jihyun
    @Operation(summary = "관리자 Q&A 답변 삭제", description = "관리자가 Q&A 답변 삭제합니다.")
    @DeleteMapping(value="/qna/{qnaIdx}/qnareply/{qnaReplyIdx}")
    public ResponseEntity<Void> replyRemove(
            @PathVariable Long qnaReplyIdx) {

        adminService.removeReply(qnaReplyIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 관리자 중고 물품 수정 (카테고리, 거래 상태만 변경 가능)  *24.02.15 jihyun
    @Operation(summary = "관리자 중고 물품 수정", description = "관리자가 중고 물품 수정합니다.")
    @PatchMapping(value="/product/{productIdx}")
    public ResponseEntity<Void> productModify(
            @PathVariable Long productIdx,
            @RequestParam CategoryName categoryName,
            @RequestParam TransactionStatus transactionStatus) {

        adminService.modifyProduct(productIdx, categoryName, transactionStatus);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 관리자 중고 물품 삭제 *24.02.15 jihyun
    @Operation(summary = "관리자 중고 물품 삭제", description = "관리자가 중고 물품 삭제합니다.")
    @DeleteMapping(value="/product/{productIdx}")
    public ResponseEntity<Void> productRemove(
            @PathVariable Long productIdx) {

        adminService.removeProduct(productIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }

    // 관리자 리뷰 삭제 *24.02.15 jihyun
    @Operation(summary = "관리자 리뷰 삭제", description = "관리자가 리뷰 삭제합니다.")
    @DeleteMapping(value = "/review/{reviewIdx}")
    public ResponseEntity<Void> reviewRemove (
            @PathVariable Long reviewIdx) {

        adminService.removeReivew(reviewIdx);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
