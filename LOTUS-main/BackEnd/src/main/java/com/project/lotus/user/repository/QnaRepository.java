package com.project.lotus.user.repository;

import com.project.lotus.auth.entity.User;
import com.project.lotus.user.entity.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    List<Qna> findAllByUser(User user);

    Optional<Qna> findByQnaIdx(Long qnaIdx);

    Page<Qna> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
