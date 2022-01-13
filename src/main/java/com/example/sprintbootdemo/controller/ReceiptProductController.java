package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.model.ReceiptProduct;
import com.example.sprintbootdemo.service.ReceiptProductService;
import com.example.sprintbootdemo.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt/product")
@Validated
public class ReceiptProductController {
    private final ReceiptService receiptService;
    private final ReceiptProductService receiptProductService;

    public ReceiptProductController(ReceiptService receiptService, ReceiptProductService receiptProductService) {
        this.receiptService = receiptService;
        this.receiptProductService = receiptProductService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReceiptProduct>> getReceiptProducts(@RequestParam(required = false) Long receiptId) {
        return ResponseEntity.ok().body(null);
    }

//    @PostMapping("")
//    public ResponseEntity<List<ReceiptProduct>> addNewReceiptProduct(@RequestParam(required = false) Long receiptId,
//                                                                     @RequestBody ReceiptProduct receiptProduct) {
//        return ResponseEntity.ok().body(receiptService.addNewReceiptProduct(receiptId, receiptProduct));
//    }
}
