package com.example.sprintbootdemo.dto;


import com.example.sprintbootdemo.model.ProductDetails;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductRequestBodyDto {
    @NotBlank
    private String productName;
    @NotBlank
    @Positive
    private Float productPrice;
    @NotBlank
    private ProductDetails productDetails;

    public ProductRequestBodyDto(String productName, Float productPrice, ProductDetails productDetails) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetails = productDetails;
    }

    public String getProductName() {
        return productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }
}
