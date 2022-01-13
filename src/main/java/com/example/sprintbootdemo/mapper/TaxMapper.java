package com.example.sprintbootdemo.mapper;

import com.example.sprintbootdemo.dto.TaxRequestBodyDto;
import com.example.sprintbootdemo.model.Tax;
import org.springframework.stereotype.Component;

@Component
public class TaxMapper {
    public Tax TaxRequestBodyDtoToTax(TaxRequestBodyDto tax) {
        return new Tax(tax.getTaxName(), tax.getTaxPercentage());
    }
}
