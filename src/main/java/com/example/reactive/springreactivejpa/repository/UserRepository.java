package com.example.reactive.springreactivejpa.repository;

import com.example.reactive.springreactivejpa.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
