package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
