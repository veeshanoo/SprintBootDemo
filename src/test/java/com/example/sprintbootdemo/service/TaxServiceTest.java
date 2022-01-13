package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.SameNameException;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.repository.TaxRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaxServiceTest {
    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private TaxService taxService;

    @Test
    @DisplayName("Tax is created successfully")
    void saveNewTaxSuccess() {
        Tax tax = new Tax("taxA", (float) 0.2);
        when(taxRepository.findByTaxName(tax.getTaxName()))
                .thenReturn(new ArrayList<>());
        when(taxRepository.save(tax)).thenReturn(tax);

        Tax newTax = taxService.saveNewTax(tax);

        assertNotNull(newTax);
        assertEquals(tax.getTaxName(), newTax.getTaxName());
        assertEquals(tax.getTaxPercentage(), newTax.getTaxPercentage());
        verify(taxRepository).findByTaxName(tax.getTaxName());
        verify(taxRepository).save(tax);
    }

    @Test
    @DisplayName("Tax name already exists")
    void saveNewTax() {
        Tax tax = new Tax("taxA", (float) 0.2);
        when(taxRepository.findByTaxName(tax.getTaxName()))
                .thenReturn(List.of(tax));

        SameNameException exception = assertThrows(SameNameException.class,
                () -> taxService.saveNewTax(tax));

        assertNotNull(exception);
        assertEquals("Tax with name: taxA already exists", exception.getMessage());
        verify(taxRepository).findByTaxName(tax.getTaxName());
        verify(taxRepository, times(0)).save(tax);
    }
}