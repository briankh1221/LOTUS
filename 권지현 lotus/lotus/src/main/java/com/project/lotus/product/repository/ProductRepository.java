package com.project.lotus.product.repository;

import com.project.lotus.auth.entity.User;
import com.project.lotus.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryIdx(Long categoryIdx);

    List<Product> findFirst10ByOrderByPostingDateDesc();

    List<Product> findAllByUser(User user);
}
