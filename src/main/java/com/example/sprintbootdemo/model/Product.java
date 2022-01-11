package com.example.sprintbootdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long productId;

    private String productName;
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

    public Product(Long productId, String productName, Float productPrice, Tax tax) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.tax = tax;
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
}
