package com.example.sprintbootdemo.dto;


import com.example.sprintbootdemo.model.ProductDetails;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ApiModel(description = "Product to be created")
public class ProductRequestBodyDto {
    @NotNull
    private String productName;
    @NotNull
    @Positive
    private Float productPrice;
    @NotNull
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
