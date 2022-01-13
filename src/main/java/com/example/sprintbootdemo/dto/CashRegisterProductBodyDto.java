package com.example.sprintbootdemo.dto;

import javax.validation.constraints.NotBlank;

public class CashRegisterProductBodyDto {
    @NotBlank
    private Long productId;

    public CashRegisterProductBodyDto() {
    }

    public CashRegisterProductBodyDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
