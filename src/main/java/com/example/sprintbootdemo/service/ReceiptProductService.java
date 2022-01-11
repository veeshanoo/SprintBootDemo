package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.model.ReceiptProduct;
import com.example.sprintbootdemo.repository.ReceiptProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceiptProductService {
    private final ReceiptProductRepository receiptProductRepository;
    private final ProductService productService;

    public ReceiptProductService(ReceiptProductRepository receiptProductRepository,
                                 ProductService productService) {
        this.receiptProductRepository = receiptProductRepository;
        this.productService = productService;
    }

    public ReceiptProduct saveNewReceiptProduct(ReceiptProduct receiptProduct) {
        return receiptProductRepository.save(receiptProduct);
    }

    public ReceiptProduct getReceiptProduct(Long receiptProductId) {
        return receiptProductRepository.findById(receiptProductId).orElseThrow(
                () -> new ResourceNotFoundException("Receipt product with id: " + receiptProductId + " does not exit")
        );
    }

    public void deleteReceiptProduct(Long receiptProductId) {
        ReceiptProduct receiptProduct = getReceiptProduct(receiptProductId);
        receiptProductRepository.delete(receiptProduct);
    }
}
