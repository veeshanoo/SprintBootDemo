package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
}
