package com.project.lotus.user.entity;

import com.project.lotus.admin.entity.QnaReply;
import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.user.dto.QnaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qna extends BaseTimeEntity {

    @Schema(description = "Q&A 인덱스", example = "1")
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

    private String images;

    @CreatedDate
    private LocalDateTime postingDate;

    @Schema(description = "qnareply_idx")
    @OneToOne
    @JoinColumn(name ="qnareply_idx")
    private QnaReply qnaReply;

    // QnaDto -> Qna(Entity)로 변환 *24.01.28 jihyun
    public static Qna from(QnaDto.Request qnaDto, User user) {

        return Qna.builder()
                .user(user)
                .title(qnaDto.getTitle())
                .content(qnaDto.getContent())
                .images(qnaDto.getImages())
                .build();
    }

    public void modifyQna(QnaDto.Request qnaDto) {

        if (qnaDto.getTitle() != null) {
            this.title = qnaDto.getTitle();
        }
        if (qnaDto.getContent() != null) {
            this.content = qnaDto.getContent();
        }
        if (qnaDto.getImages() != null) {
            this.images = qnaDto.getImages();
        }
    }
}
