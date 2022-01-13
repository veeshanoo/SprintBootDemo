package com.example.sprintbootdemo.mapper;

import com.example.sprintbootdemo.dto.ProductRequestBodyDto;
import com.example.sprintbootdemo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product productRequestBodyDtoToProduct(ProductRequestBodyDto request) {
        return new Product(request.getProductName(), request.getProductPrice(), request.getProductDetails());
    }
}
