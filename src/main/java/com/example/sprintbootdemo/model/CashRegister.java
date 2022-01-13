package com.example.sprintbootdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class CashRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long cashRegisterId;

    @OneToMany(mappedBy = "cashRegister")
    @JsonIgnore
    private List<Receipt> receipts;

    @ManyToMany
    @JoinTable(
            name = "cash_register_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cash_register_id")
    )
    private List<Product> products;

    public CashRegister() {
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }
}
