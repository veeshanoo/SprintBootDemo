package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + productId + " does not exist")
        );
    }

    public Product updateProduct(Long productId, Product product) {
        Product oldProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + productId + " does not exist")
        );

        if (product.getProductName() != null) {
            oldProduct.setProductName(product.getProductName());
        }
        if (product.getProductPrice() != null) {
            oldProduct.setProductPrice(product.getProductPrice());
        }
        if (product.getTax() != null) {
            oldProduct.setTax(product.getTax());
        }

        oldProduct.setProductPrice(product.getProductPrice());

        return productRepository.save(oldProduct);
    }

    public Product saveNewProduct(Product product, Long taxId) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }
}
