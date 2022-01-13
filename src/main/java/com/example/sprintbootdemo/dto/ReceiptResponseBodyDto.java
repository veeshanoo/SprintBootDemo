package com.example.sprintbootdemo.dto;

import java.util.List;

public class ReceiptResponseBodyDto {
    private Long receiptId;
    private Long cashRegisterId;
    private List<ReceiptProductResponseBodyDto> products;

    public ReceiptResponseBodyDto(Long receiptId, Long cashRegisterId, List<ReceiptProductResponseBodyDto> products) {
        this.receiptId = receiptId;
        this.cashRegisterId = cashRegisterId;
        this.products = products;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public Long getCashRegisterId() {
        return cashRegisterId;
    }

    public List<ReceiptProductResponseBodyDto> getProducts() {
        return products;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public void setCashRegisterId(Long cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }

    public void setProducts(List<ReceiptProductResponseBodyDto> products) {
        this.products = products;
    }
}
