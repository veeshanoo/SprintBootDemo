package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.CashRegisterProductBodyDto;
import com.example.sprintbootdemo.dto.ReceiptResponseBodyDto;
import com.example.sprintbootdemo.mapper.ReceiptMapper;
import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.CashRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cashRegister")
@Api(description = "Exposes cash register operations", tags = "Cash register")
public class CashRegisterController {
    private final CashRegisterService cashRegisterService;
    private final ReceiptMapper receiptMapper;

    public CashRegisterController(CashRegisterService cashRegisterService, ReceiptMapper receiptMapper) {
        this.cashRegisterService = cashRegisterService;
        this.receiptMapper = receiptMapper;
    }

    @GetMapping("/{id}/receipt")
    @ApiOperation(value = "Gets all receipts for cash register with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
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
    @ApiOperation(value = "Gets all products available for cash register with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<List<Product>> getCashRegisterProducts(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegister(id).getProducts());
    }

    @GetMapping("/{id}/sales")
    @ApiOperation(value = "Computes total for cash register with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Float> getCashRegisterTotal(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegisterTotal(id));
    }

    @GetMapping("/{id}/tax")
    @ApiOperation(value = "Computes total tax for cash register with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Float> getCashRegisterTotalTax(@PathVariable Long id) {
        return ResponseEntity.ok().body(cashRegisterService.getCashRegisterTotal(id));
    }

    @PostMapping("{id}/product")
    @ApiOperation("Adds new product for cash register with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public void addNewProductToCashRegister(@PathVariable Long id,
                                            @RequestBody CashRegisterProductBodyDto requestBody) {
        System.out.println(requestBody.getProductId());
        cashRegisterService.addNewProduct(id, requestBody.getProductId());
    }

    @DeleteMapping("{id}/product")
    @ApiOperation(value = "Deletes a product for cash register with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public void deleteProductFromCashRegister(@PathVariable Long id,
                                              @RequestBody CashRegisterProductBodyDto requestBody) {
        cashRegisterService.deleteProduct(id, requestBody.getProductId());
    }
}
