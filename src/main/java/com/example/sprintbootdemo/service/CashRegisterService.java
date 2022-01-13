package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.repository.CashRegisterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;
    private final ReceiptService receiptService;
    private final ProductService productService;

    public CashRegisterService(CashRegisterRepository cashRegisterRepository, ReceiptService receiptService, ProductService productService) {
        this.cashRegisterRepository = cashRegisterRepository;
        this.receiptService = receiptService;
        this.productService = productService;
    }

    public CashRegister saveNewCashRegister(CashRegister cashRegister) {
        return cashRegisterRepository.save(cashRegister);
    }

    public CashRegister getCashRegister(Long cashRegisterId) {
        return cashRegisterRepository.findById(cashRegisterId).orElseThrow(
                () -> new ResourceNotFoundException("Cash register with id: " + cashRegisterId + " does not exist")
        );
    }

    public Float getCashRegisterTotal(Long cashRegisterId) {
        CashRegister cashRegister = getCashRegister(cashRegisterId);
        Float total = (float) 0;

        for (Receipt receipt : cashRegister.getReceipts()) {
            total += receipt.calculateReceiptTotal();
        }

        return total;
    }

    public Receipt createNewReceipt(Long cashRegisterId, Receipt receipt) {
        receipt.setCashRegister(getCashRegister(cashRegisterId));
        return receiptService.saveNewReceipt(receipt);
    }

    public boolean hasProduct(Long cashRegisterId, Long productId) {
        CashRegister cashRegister = getCashRegister(cashRegisterId);
        if (cashRegister.getProducts() == null) {
            return false;
        }

        return cashRegister.getProducts().stream().anyMatch((e) -> {
            return e.getProductId().equals(productId);
        });
    }

    public void addNewProduct(Long cashRegisterId, Long productId) {
        CashRegister cashRegister = getCashRegister(cashRegisterId);
        Product product = productService.getProduct(productId);

        if (hasProduct(cashRegisterId, productId)) {
            return;
        }

        if (cashRegister.getProducts() == null) {
            cashRegister.setProducts(new ArrayList<>());
        }

        cashRegister.getProducts().add(product);
        cashRegisterRepository.save(cashRegister);
    }


    public void deleteProduct(Long cashRegisterId, Long productId) {
        if (!hasProduct(cashRegisterId, productId)) {
            return;
        }

        CashRegister cashRegister = getCashRegister(cashRegisterId);
        List<Product> newProducts = cashRegister.getProducts().stream().filter((e) -> {
            return !e.getProductId().equals(productId);
        }).collect(Collectors.toList());

        cashRegister.setProducts(newProducts);
        cashRegisterRepository.save(cashRegister);
    }
}
