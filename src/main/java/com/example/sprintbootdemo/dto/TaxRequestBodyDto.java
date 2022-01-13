package com.example.sprintbootdemo.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TaxRequestBodyDto {
    @NotNull(message = "taxName field is mandatory")
    private String taxName;
    @NotNull(message = "taxPercentage field is mandatory")
    @Range(min = 0, max = 1)
    private Float taxPercentage;

    public TaxRequestBodyDto() {
    }

    public TaxRequestBodyDto(String taxName, Float taxPercentage) {
        this.taxName = taxName;
        this.taxPercentage = taxPercentage;
    }

    public String getTaxName() {
        return taxName;
    }

    public Float getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public void setTaxPercentage(Float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
}
