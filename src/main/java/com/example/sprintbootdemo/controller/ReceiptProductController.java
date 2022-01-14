package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ReceiptProductRequestBodyDto;
import com.example.sprintbootdemo.dto.ReceiptProductResponseBodyDto;
import com.example.sprintbootdemo.exception.NoProductOnCashRegisterException;
import com.example.sprintbootdemo.mapper.ReceiptProductMapper;
import com.example.sprintbootdemo.model.ReceiptProduct;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.CashRegisterService;
import com.example.sprintbootdemo.service.ReceiptProductService;
import com.example.sprintbootdemo.service.ReceiptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/receipt/product")
@Validated
@Api(description = "Exposes product operations on receipt", tags = "Receipt products")
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
    @ApiOperation(value = "Get receipt product with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<ReceiptProductResponseBodyDto> getReceiptProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptProductMapper.ReceiptProductToReceiptProductResponseBodyDto(
                receiptProductService.getReceiptProduct(id)
        ));
    }

    @PostMapping("")
    @ApiOperation(value = "Adds new product to receipt")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 201, message = "Successful creation", response = Tax.class),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
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
    @ApiOperation("Deletes receipt product with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public void deleteReceiptProduct(@PathVariable Long id) {
        receiptProductService.deleteReceiptProduct(id);
    }
}
