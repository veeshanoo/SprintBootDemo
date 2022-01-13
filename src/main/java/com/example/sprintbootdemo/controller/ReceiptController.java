package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ReceiptProductResponseBodyDto;
import com.example.sprintbootdemo.dto.ReceiptResponseBodyDto;
import com.example.sprintbootdemo.mapper.ReceiptMapper;
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
    private final ReceiptMapper receiptMapper;

    public ReceiptController(ReceiptService receiptService, CashRegisterService cashRegisterService, ReceiptMapper receiptMapper) {
        this.receiptService = receiptService;
        this.cashRegisterService = cashRegisterService;
        this.receiptMapper = receiptMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponseBodyDto> getReceipt(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptMapper.ReceiptToReceiptResponseBodyDto(receiptService.getReceipt(id)));
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
    public ResponseEntity<ReceiptResponseBodyDto> createNewReceipt(@RequestParam(required = true) Long cashRegisterId) {
        return ResponseEntity.ok().body(receiptMapper.ReceiptToReceiptResponseBodyDto(
                cashRegisterService.createNewReceipt(cashRegisterId, new Receipt())
        ));
    }
}
