package com.project.lotus.user.repository;

import com.project.lotus.auth.entity.User;
import com.project.lotus.user.entity.Favorite;
import com.project.lotus.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByProduct(Product product);

    Optional<Favorite> findByUserAndProduct(User user, Product product);

    List<Favorite> findAllByUser(User user);

    @Query("SELECT p " + "FROM Favorite f " + "JOIN f.product p " + "GROUP BY p.productIdx " + "ORDER BY count(p) DESC")
    List<Product> findProductsOrderedByCountDesc();
}

