package com.tejendra.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejendra.jobportal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}