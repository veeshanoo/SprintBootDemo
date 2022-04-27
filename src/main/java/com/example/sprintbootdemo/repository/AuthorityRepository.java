package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
