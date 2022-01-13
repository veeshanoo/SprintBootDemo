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

    public Long getCashRegisterId() {
        return cashRegisterId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setCashRegisterId(Long cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
