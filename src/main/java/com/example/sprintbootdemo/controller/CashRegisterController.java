package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.service.CashRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cashRegister")
public class CashRegisterController {
    private final CashRegisterService cashRegisterService;

    public CashRegisterController(CashRegisterService cashRegisterService) {
        this.cashRegisterService = cashRegisterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Receipt>> getCashRegister(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegister(id).getReceipts());
    }

    @GetMapping("/{id}/sales")
    public ResponseEntity<Float> getCashRegisterTotal(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegisterTotal(id));
    }

    @GetMapping("/{id}/tax")
    public ResponseEntity<Float> getCashRegisterTotalTax(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegisterTotal(id));
    }
}
