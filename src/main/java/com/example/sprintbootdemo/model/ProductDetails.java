package com.example.sprintbootdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "Product details data")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long productDetailsId;

    private String description;

    @OneToOne(mappedBy = "productDetails")
    @JsonIgnore
    private Product product;

    public ProductDetails() {
    }

    public ProductDetails(String description, Product product) {
        this.description = description;
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public Product getProduct() {
        return product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
