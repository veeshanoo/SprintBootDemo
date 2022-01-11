package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping("")
    public ResponseEntity<Product> saveNewProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.saveNewProduct(product));
    }
}
