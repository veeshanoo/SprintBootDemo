package com.example.sprintbootdemo.service;

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

    public Optional<Tax> getTax(Long taxId) {
        System.out.println("ASDASD " + taxId.toString());
        Optional<Tax> yes = taxRepository.findById(taxId);
        System.out.println(yes);

        return yes;
    }

    public Tax saveNewTax(Tax tax) {
        return taxRepository.save(tax);
    }
}
