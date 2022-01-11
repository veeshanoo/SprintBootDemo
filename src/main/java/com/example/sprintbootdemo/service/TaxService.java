package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.repository.TaxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxService {
    private final TaxRepository taxRepository;

    public TaxService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }

    public Tax getTax(Long taxId) {
        return taxRepository.findById(taxId).orElseThrow(
                () -> new ResourceNotFoundException("Tax with id: " + taxId + " does not exist")
        );
    }

    public Tax saveNewTax(Tax tax) {
        return taxRepository.save(tax);
    }
}
