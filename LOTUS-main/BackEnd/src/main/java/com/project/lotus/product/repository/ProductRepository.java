package com.project.lotus.product.repository;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.enums.CategoryName;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryName(CategoryName categoryName);

    List<Product> findFirst8ByOrderByPostingDateDesc();

    List<Product> findAllByUser(User user);

    List<Product> findAllByUserAndTransactionStatus(User user, TransactionStatus transactionStatus);

    List<Product> findAllByBuyer_idxAndTransactionStatus(Long buyer_idx, TransactionStatus transactionStatus);

    Optional<Product> findByProductIdx(Long productIdx);

    Page<Product> findByProductNameContainingOrTitleContainingOrDescriptionContaining (String productName, String title, String description, Pageable pageable);

    List<Product> findAllByOrderByPostingDateDesc();
}
