package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.TaxRequestBodyDto;
import com.example.sprintbootdemo.mapper.TaxMapper;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.TaxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tax")
@Validated
@Api(description = "Exposes product tax operations", tags = "Tax")
public class TaxController {
    private final TaxService taxService;
    private final TaxMapper taxMapper;

    public TaxController(TaxService taxService, TaxMapper taxMapper) {
        this.taxService = taxService;
        this.taxMapper = taxMapper;
    }

    @GetMapping("")
    @ApiOperation(value = "Gets all existent taxes")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<List<Tax>> getAllTaxes() {
        return ResponseEntity.ok().body(taxService.getAllTaxes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Gets tax by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Tax> getTax(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(taxService.getTax(id));
    }

    @PostMapping("")
    @ApiOperation(value = "Creates new tax")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 201, message = "Successful creation", response = Tax.class),
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Specified resource does not exist")
    })
    public ResponseEntity<Tax> saveNewTax(@Valid @RequestBody TaxRequestBodyDto requestBodyDto) {
        Tax tax = taxService.saveNewTax(taxMapper.TaxRequestBodyDtoToTax(requestBodyDto));
        return ResponseEntity.created(URI.create("/api/tax/" + tax.getTaxId())).body(tax);
    }
}
