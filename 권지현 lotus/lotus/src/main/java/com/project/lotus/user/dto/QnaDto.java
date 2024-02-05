package com.project.lotus.user.dto;

import com.project.lotus.user.entity.Qna;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class QnaDto {

    // Q&A 인덱스 *24.01.28 jihyun
    private Long qnaIdx;

    // Q&A 제목 *24.01.28 jihyun
    private String title;

    // Q&A 내용 *24.01.28 jihyun
    private String content;

    // Q&A 이미지 *24.01.28 jihyun
    private String image;

    // 생성일 *24.01.19 jihyun
    private String postingDate;

    // QnaForm -> QnaDto로 변환 *24.01.28 jihyun
    public static QnaDto from(QnaForm qnaForm) {

        return QnaDto.builder()
                .qnaIdx(qnaForm.getQnaIdx())
                .title(qnaForm.getTitle())
                .content(qnaForm.getContent())
                .image(qnaForm.getImage())
                .postingDate(qnaForm.getPostingDate())
                .build();
    }

    // Qna(Entity) -> QnaDto로 변환 *24.01.28 jihyun
    public static QnaDto from(Qna qna) {

        return QnaDto.builder()
                .qnaIdx(qna.getQnaIdx())
                .title(qna.getTitle())
                .content(qna.getContent())
                .image(qna.getImage())
                .postingDate(qna.getPostingDate().toString())
                .build();
    }
}
