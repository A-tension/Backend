package com.b5f1.atention.domain.user.repository;

import com.b5f1.atention.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByEmail(String email);

    Optional<User> findByEmail(String email);
}