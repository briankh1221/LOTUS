package com.project.lotus.admin.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class QnaReply extends BaseTimeEntity {

    // Q&A 인덱스 *24.01.28 jihyun
    private Long qnaIdx;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    // 관리자 답변 *24.01.30 jihyun
    private String reply;

    @NotBlank
    // 생성일 *24.01.30 jihyun
    private LocalDateTime postingDate;
}
