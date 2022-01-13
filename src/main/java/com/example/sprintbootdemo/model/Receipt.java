package com.example.sprintbootdemo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptProduct> receiptProducts;

    @ManyToOne
    @JoinColumn(name = "cash_register_id")
    private CashRegister cashRegister;

    public Receipt() {
    }

    public Receipt(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }

    public void setCashRegister(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }

    public List<ReceiptProduct> getReceiptProducts() {
        return receiptProducts;
    }

    public Float calculateReceiptTotal() {
        Float total = (float) 0;

        for (ReceiptProduct receiptProduct : this.getReceiptProducts()) {
            total += receiptProduct.calculateCost();
        }

        return total;
    }

    public Float calculateReceiptTax() {
        Float tax = (float) 0;

        for (ReceiptProduct receiptProduct : this.getReceiptProducts()) {
            tax += receiptProduct.calculateTax();
        }

        return tax;
    }
}
