package com.project.lotus.user.repository;

import com.project.lotus.product.entity.Product;
import com.project.lotus.user.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProduct(Product product);

    Optional<List<Review>> findByProduct_User_IdxOrUser_Idx(Long BuyerIdx, Long Selleridx);

    Page<Review> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
