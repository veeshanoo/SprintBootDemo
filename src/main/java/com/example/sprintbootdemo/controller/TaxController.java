package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.TaxRequestBodyDto;
import com.example.sprintbootdemo.mapper.TaxMapper;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tax")
@Validated
public class TaxController {
    private final TaxService taxService;
    private final TaxMapper taxMapper;

    public TaxController(TaxService taxService, TaxMapper taxMapper) {
        this.taxService = taxService;
        this.taxMapper = taxMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<Tax>> getAllTaxes() {
        return ResponseEntity.ok().body(taxService.getAllTaxes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTax(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(taxService.getTax(id));
    }

    @PostMapping("")
    public ResponseEntity<Tax> saveNewTax(@Valid @RequestBody TaxRequestBodyDto requestBodyDto) {
        return ResponseEntity.ok().body(taxService.saveNewTax(taxMapper.TaxRequestBodyDtoToTax(requestBodyDto)));
    }
}
