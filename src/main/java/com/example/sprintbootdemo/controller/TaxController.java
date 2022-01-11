package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax")
@Validated
public class TaxController {
    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
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
    public ResponseEntity<Tax> saveNewTax(@RequestBody Tax tax) {
        return ResponseEntity.ok().body(taxService.saveNewTax(tax));
    }
}
