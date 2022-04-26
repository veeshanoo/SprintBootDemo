package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.TaxRequestBodyDto;
import com.example.sprintbootdemo.mapper.TaxMapper;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.TaxService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxControllerTest {
    @Mock
    private TaxMapper taxMapper;

    @Mock
    private TaxService taxService;

    @InjectMocks
    private TaxController taxController;

    @Test
    @DisplayName("succesful tax creation")
    public void testTaxCreation() {
        TaxRequestBodyDto dto = new TaxRequestBodyDto("TaxA", (float) 0.2);
        Tax tax = new Tax("TaxA", (float) 0.2);
        tax.setTaxId(1L);

        when(taxMapper.TaxRequestBodyDtoToTax(dto)).thenReturn(tax);
        when(taxService.saveNewTax(tax)).thenReturn(tax);

        ResponseEntity<Tax> response = taxController.saveNewTax(dto);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getHeaders().getLocation(), URI.create("/api/tax/1"));
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getTaxName(), tax.getTaxName());
        assertEquals(response.getBody().getTaxPercentage(), tax.getTaxPercentage());
        verify(taxService).saveNewTax(tax);
    }
}