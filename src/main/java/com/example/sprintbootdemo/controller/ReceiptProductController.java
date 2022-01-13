package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ReceiptProductRequestBodyDto;
import com.example.sprintbootdemo.dto.ReceiptProductResponseBodyDto;
import com.example.sprintbootdemo.exception.NoProductOnCashRegisterException;
import com.example.sprintbootdemo.mapper.ReceiptProductMapper;
import com.example.sprintbootdemo.model.ReceiptProduct;
import com.example.sprintbootdemo.service.CashRegisterService;
import com.example.sprintbootdemo.service.ReceiptProductService;
import com.example.sprintbootdemo.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/receipt/product")
@Validated
public class ReceiptProductController {
    private final ReceiptService receiptService;
    private final ReceiptProductService receiptProductService;
    private final ReceiptProductMapper receiptProductMapper;
    private final CashRegisterService cashRegisterService;

    public ReceiptProductController(ReceiptService receiptService, ReceiptProductService receiptProductService, ReceiptProductMapper receiptProductMapper, CashRegisterService cashRegisterService) {
        this.receiptService = receiptService;
        this.receiptProductService = receiptProductService;
        this.receiptProductMapper = receiptProductMapper;
        this.cashRegisterService = cashRegisterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptProductResponseBodyDto> getReceiptProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptProductMapper.ReceiptProductToReceiptProductResponseBodyDto(
                receiptProductService.getReceiptProduct(id)
        ));
    }

    @PostMapping("")
    public ResponseEntity<ReceiptProductResponseBodyDto> addNewReceiptProduct(@RequestBody ReceiptProductRequestBodyDto requestBody) {
        ReceiptProduct receiptProduct = new ReceiptProduct();
        receiptProduct.setReceiptProductQuantity(requestBody.getReceiptProductQuantity());

        Long cashRegisterId = receiptService.getReceipt(requestBody.getReceiptId()).getCashRegister().getCashRegisterId();
        if (!cashRegisterService.hasProduct(cashRegisterId, requestBody.getProductId())) {
            throw new NoProductOnCashRegisterException("Product with id: " + requestBody.getProductId() + " does not exist on cash register with id:" + cashRegisterId);
        }

        ReceiptProduct savedReceiptProduct = receiptService.addNewReceiptProduct(
                requestBody.getReceiptId(),
                requestBody.getProductId(),
                receiptProduct);

        return ResponseEntity.created(URI.create("/api/receipt/product/" + savedReceiptProduct.getReceiptProductId()))
                .body(receiptProductMapper.ReceiptProductToReceiptProductResponseBodyDto(
                        savedReceiptProduct));
    }

    @DeleteMapping("{id}")
    public void deleteReceiptProduct(@PathVariable Long id) {
        receiptProductService.deleteReceiptProduct(id);
    }
}
