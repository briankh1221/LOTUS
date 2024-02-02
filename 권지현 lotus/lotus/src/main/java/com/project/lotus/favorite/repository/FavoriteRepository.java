package com.project.lotus.favorite.repository;

import com.project.lotus.auth.entity.User;
import com.project.lotus.favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByUser(User user);

}
