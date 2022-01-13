package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.service.CashRegisterService;
import com.example.sprintbootdemo.service.ReceiptService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipt")
@Validated
public class ReceiptController {
    private final ReceiptService receiptService;
    private final CashRegisterService cashRegisterService;

    public ReceiptController(ReceiptService receiptService, CashRegisterService cashRegisterService) {
        this.receiptService = receiptService;
        this.cashRegisterService = cashRegisterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptService.getReceipt(id));
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Float> getReceiptTotal(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptService.getReceipt(id).calculateReceiptTotal());
    }

    @GetMapping("/{id}/tax")
    public ResponseEntity<Float> getReceiptTax(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptService.getReceipt(id).calculateReceiptTax());
    }

    @DeleteMapping("/{id}")
    public void deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
    }

    @PostMapping("")
    public ResponseEntity<Receipt> createNewReceipt(@RequestParam(required = true) Long cashRegisterId) {
        return ResponseEntity.ok().body(cashRegisterService.createNewReceipt(cashRegisterId, new Receipt()));
    }
}
