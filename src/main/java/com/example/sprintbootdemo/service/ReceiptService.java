package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.model.ReceiptProduct;
import com.example.sprintbootdemo.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptProductService receiptProductService;

    public ReceiptService(ReceiptRepository receiptRepository,
                          ReceiptProductService receiptProductService) {
        this.receiptRepository = receiptRepository;
        this.receiptProductService = receiptProductService;
    }

    public Receipt saveNewReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceipt(Long receiptId) {
        return receiptRepository.findById(receiptId).orElseThrow(
                () -> new ResourceNotFoundException("Receipt with id: " + receiptId + " does not exist")
        );
    }

    public ReceiptProduct addNewReceiptProduct(Long receiptId, Long productId, ReceiptProduct receiptProduct) {
        Receipt receipt = getReceipt(receiptId);
        receiptProduct.setReceipt(receipt);

        return receiptProductService.saveNewReceiptProduct(productId, receiptProduct);
    }

    public void deleteReceipt(Long receiptId) {
        Receipt receipt = getReceipt(receiptId);
        receiptRepository.delete(receipt);
    }
}
