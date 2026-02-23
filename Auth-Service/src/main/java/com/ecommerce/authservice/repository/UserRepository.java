package com.ecommerce.authservice.repository;

import com.ecommerce.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<com.ecommerce.authservice.model.User> findUserByUsername(String username);

    boolean existsByUsername(String username);
}
