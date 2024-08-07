package com.project.lotus.admin.repository;

import com.project.lotus.admin.entity.QnaReply;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface QnaReplyRepository extends JpaRepository<QnaReply,Long> {

    Optional<QnaReply> findByQnaReplyIdx(Long qnaReplyIdx);

    List<QnaReply> findAllByOrderByPostingDateDesc();
}
