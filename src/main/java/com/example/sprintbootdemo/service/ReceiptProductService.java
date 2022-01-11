package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.repository.ReceiptProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceiptProductService {
    private final ReceiptProductRepository receiptProductRepository;

    public ReceiptProductService(ReceiptProductRepository receiptProductRepository) {
        this.receiptProductRepository = receiptProductRepository;
    }
}
