package com.project.lotus.admin.entity;

import com.project.lotus.admin.dto.QnaReplyDto;
import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaReply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaReplyIdx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Long qna_idx;

    @NotBlank
    private String reply;

    @CreatedDate
    private LocalDateTime postingDate;

    // QnaReplyDto.Request -> QnaReply로 변환 *24.02.01 jihyun
    public static QnaReply from(QnaReplyDto.Request qnaReplyDto, User user, Long qnaIdx) {

        return QnaReply.builder()
                .reply(qnaReplyDto.getReply())
                .user(user)
                .qna_idx(qnaIdx)
                .build();
    }

    public void modifyQnaReply(QnaReplyDto.Request qnaReplyDto) {

        if (qnaReplyDto.getReply() != null) {
            this.reply = qnaReplyDto.getReply();
        }
    }
}
