package com.example.project.domain.user.repository;

import com.example.project.domain.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

  boolean existsByUserId(Long userId);

  Optional<User> findByEmail(String email);
}
