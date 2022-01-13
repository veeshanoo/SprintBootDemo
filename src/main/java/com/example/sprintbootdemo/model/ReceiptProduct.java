package com.example.sprintbootdemo.model;

import javax.persistence.*;

@Entity
public class ReceiptProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptProductId;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Float receiptProductQuantity;

    public ReceiptProduct() {
    }

    public ReceiptProduct(Receipt receipt, Product product, Float receiptProductQuantity) {
        this.receipt = receipt;
        this.product = product;
        this.receiptProductQuantity = receiptProductQuantity;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Product getProduct() {
        return product;
    }

    public Float getReceiptProductQuantity() {
        return receiptProductQuantity;
    }

    public Long getReceiptProductId() {
        return receiptProductId;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setReceiptProductQuantity(Float receiptProductQuantity) {
        this.receiptProductQuantity = receiptProductQuantity;
    }

    public Float calculateTax() {
        if (this.product.getTax() == null) {
            return (float) 0.0;
        }

        return this.getReceiptProductQuantity() * this.product.getProductPrice() * this.product.getTax().getTaxPercentage();
    }

    public Float calculateCostWithoutTax() {
        return this.getReceiptProductQuantity() * this.product.getProductPrice();
    }

    public Float calculateCost() {
        return this.calculateCostWithoutTax() + this.calculateTax();
    }
}
