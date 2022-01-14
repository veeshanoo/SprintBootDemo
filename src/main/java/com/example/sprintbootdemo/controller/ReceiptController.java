package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ReceiptProductResponseBodyDto;
import com.example.sprintbootdemo.dto.ReceiptResponseBodyDto;
import com.example.sprintbootdemo.mapper.ReceiptMapper;
import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.CashRegisterService;
import com.example.sprintbootdemo.service.ReceiptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipt")
@Validated
@Api(description = "Exposes receipt operations", tags = "Receipt")
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
    @ApiOperation(value = "Gets receipt with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<ReceiptResponseBodyDto> getReceipt(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptMapper.ReceiptToReceiptResponseBodyDto(receiptService.getReceipt(id)));
    }

    @GetMapping("/{id}/total")
    @ApiOperation("Calculates total for receipt with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Float> getReceiptTotal(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptService.getReceipt(id).calculateReceiptTotal());
    }

    @GetMapping("/{id}/tax")
    @ApiOperation(value = "Calculates total tax for receipt with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Float> getReceiptTax(@PathVariable Long id) {
        return ResponseEntity.ok().body(receiptService.getReceipt(id).calculateReceiptTax());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes receipt with ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public void deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
    }

    @PostMapping("")
    @ApiOperation("Creates new receipt")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 201, message = "Successful creation", response = Tax.class),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<ReceiptResponseBodyDto> createNewReceipt(@RequestParam(required = true) Long cashRegisterId) {
        return ResponseEntity.ok().body(receiptMapper.ReceiptToReceiptResponseBodyDto(
                cashRegisterService.createNewReceipt(cashRegisterId, new Receipt())
        ));
    }
}
