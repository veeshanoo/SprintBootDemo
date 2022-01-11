package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
