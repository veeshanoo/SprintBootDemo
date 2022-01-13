package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.CashRegisterProductBodyDto;
import com.example.sprintbootdemo.dto.ReceiptResponseBodyDto;
import com.example.sprintbootdemo.mapper.ReceiptMapper;
import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.service.CashRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cashRegister")
public class CashRegisterController {
    private final CashRegisterService cashRegisterService;
    private final ReceiptMapper receiptMapper;

    public CashRegisterController(CashRegisterService cashRegisterService, ReceiptMapper receiptMapper) {
        this.cashRegisterService = cashRegisterService;
        this.receiptMapper = receiptMapper;
    }

    @GetMapping("/{id}/receipt")
    public ResponseEntity<List<ReceiptResponseBodyDto>> getCashRegister(@PathVariable Long id) {
        List<Receipt> receipts = cashRegisterService.getCashRegister(id).getReceipts();
        if (receipts == null) {
            receipts = new ArrayList<>();
        }
        return ResponseEntity.ok().body(receipts.stream().
                map(receiptMapper::ReceiptToReceiptResponseBodyDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}/product")
    public ResponseEntity<List<Product>> getCashRegisterProducts(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegister(id).getProducts());
    }

    @GetMapping("/{id}/sales")
    public ResponseEntity<Float> getCashRegisterTotal(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegisterTotal(id));
    }

    @GetMapping("/{id}/tax")
    public ResponseEntity<Float> getCashRegisterTotalTax(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegisterTotal(id));
    }

    @PostMapping("{id}/product")
    public void addNewProductToCashRegister(@PathVariable Long id,
                                            @RequestBody CashRegisterProductBodyDto requestBody) {
        System.out.println(requestBody.getProductId());
        cashRegisterService.addNewProduct(id, requestBody.getProductId());
    }

    @DeleteMapping("{id}/product")
    public void deleteProductFromCashRegister(@PathVariable Long id,
                                              @RequestBody CashRegisterProductBodyDto requestBody) {
        cashRegisterService.deleteProduct(id, requestBody.getProductId());
    }
}
