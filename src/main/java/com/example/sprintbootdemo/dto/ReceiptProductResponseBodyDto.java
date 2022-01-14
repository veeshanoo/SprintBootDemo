package com.example.sprintbootdemo.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Receipt product data")
public class ReceiptProductResponseBodyDto {
    private Long receiptProductId;
    private Long receiptId;
    private Long productId;
    private Float receiptProductQuantity;

    public ReceiptProductResponseBodyDto(Long receiptProductId, Long receiptId, Long productId, Float receiptProductQuantity) {
        this.receiptProductId = receiptProductId;
        this.receiptId = receiptId;
        this.productId = productId;
        this.receiptProductQuantity = receiptProductQuantity;
    }

    public Long getReceiptProductId() {
        return receiptProductId;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public Long getProductId() {
        return productId;
    }

    public Float getReceiptProductQuantity() {
        return receiptProductQuantity;
    }

    public void setReceiptProductId(Long receiptProductId) {
        this.receiptProductId = receiptProductId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setReceiptProductQuantity(Float receiptProductQuantity) {
        this.receiptProductQuantity = receiptProductQuantity;
    }
}
