package com.example.sprintbootdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@ApiModel(description = "Product data")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long productId;

    @NotNull
    private String productName;
    @NotNull
    private Float productPrice;

    @ManyToOne
    @JoinColumn(name = "tax_id")
    private Tax tax;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;

    @ManyToMany(mappedBy = "products")
    private List<CashRegister> cashRegisters;

    @OneToMany(mappedBy = "product")
    private List<ReceiptProduct> receiptProducts;

    public Product() {
    }

    public Product(String productName, Float productPrice, ProductDetails productDetails) {
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

    public Tax getTax() {
        return tax;
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

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
