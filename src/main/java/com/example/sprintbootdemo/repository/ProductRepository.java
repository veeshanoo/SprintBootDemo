package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
//    Page<Product> findAllByProductName(String name, Pageable pageable);
}
