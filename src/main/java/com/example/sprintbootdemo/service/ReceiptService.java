package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }
}
