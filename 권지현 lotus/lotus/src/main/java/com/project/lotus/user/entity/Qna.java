package com.project.lotus.user.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.user.dto.QnaDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Qna extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaIdx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private String image;

    @NotNull
    private LocalDateTime postingDate;

    // QnaDto -> Qna(Entity)로 변환 *24.01.28 jihyun
    public static Qna from(QnaDto qnaDto, User user) {

        return Qna.builder()
                .user(user)
                .title(qnaDto.getTitle())
                .content(qnaDto.getContent())
                .image(qnaDto.getImage())
                .postingDate(LocalDateTime.parse(qnaDto.getPostingDate()))
                .build();
    }

    public void modifyQna(QnaDto qnaDto) {

        if (qnaDto.getTitle() != null) {
            this.title = qnaDto.getTitle();
        }
        if (qnaDto.getContent() != null) {
            this.content = qnaDto.getContent();
        }
    }
}
