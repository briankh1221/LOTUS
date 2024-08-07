package com.project.lotus.auth.repository;

import com.project.lotus.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    Optional<User> findByIdx(Long idx);

    Optional<User> findByEmail(String email);
}
