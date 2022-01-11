package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.repository.ProductDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsService {
    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsService(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }
}
