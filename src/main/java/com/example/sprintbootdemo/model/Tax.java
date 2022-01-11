package com.example.sprintbootdemo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxId;
    private String taxName;
    private Float taxPercentage; // (0, 1) range
    @OneToMany(mappedBy = "tax")
    private List<Product> products = new ArrayList<>();;

    public Tax() {
    }

    public Tax(Long taxId, String taxName, Float taxPercentage) {
        this.taxId = taxId;
        this.taxName = taxName;
        this.taxPercentage = taxPercentage;
    }

    public Long getTaxId() {
        return taxId;
    }

    public String getTaxName() {
        return taxName;
    }

    public Float getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public void setTaxPercentage(Float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
}