package com.example.sprintbootdemo.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Product to be added to cash register")
public class CashRegisterProductBodyDto {
    @NotNull
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
