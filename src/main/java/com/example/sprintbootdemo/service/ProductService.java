package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveNewProduct(Product product) {
        return productRepository.save(product);
    }
}
