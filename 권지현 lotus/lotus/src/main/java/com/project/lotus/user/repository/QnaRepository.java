package com.project.lotus.user.repository;

import com.project.lotus.auth.entity.User;
import com.project.lotus.review.entity.Review;
import com.project.lotus.user.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    List<Qna> findAllByUser(User user);
}
