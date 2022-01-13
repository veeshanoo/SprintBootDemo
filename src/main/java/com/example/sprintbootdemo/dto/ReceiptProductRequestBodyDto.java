package com.example.sprintbootdemo.dto;

import javax.validation.constraints.NotBlank;

public class ReceiptProductRequestBodyDto {
    @NotBlank
    private Float receiptProductQuantity;
    @NotBlank
    private Long receiptId;
    @NotBlank
    private Long productId;

    public ReceiptProductRequestBodyDto(Float receiptProductQuantity, Long receiptId, Long productId) {
        this.receiptProductQuantity = receiptProductQuantity;
        this.receiptId = receiptId;
        this.productId = productId;
    }

    public Float getReceiptProductQuantity() {
        return receiptProductQuantity;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setReceiptProductQuantity(Float receiptProductQuantity) {
        this.receiptProductQuantity = receiptProductQuantity;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
