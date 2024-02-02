package com.project.lotus.review.repository;

import com.project.lotus.product.entity.Product;
import com.project.lotus.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProduct(Product product);

}
