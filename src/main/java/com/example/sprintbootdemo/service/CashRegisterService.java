package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.repository.CashRegisterRepository;
import org.springframework.stereotype.Service;

@Service
public class CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;
    private final ReceiptService receiptService;

    public CashRegisterService(CashRegisterRepository cashRegisterRepository,
                               ReceiptService receiptService) {
        this.cashRegisterRepository = cashRegisterRepository;
        this.receiptService = receiptService;
    }

    public CashRegister saveNewCashRegister(CashRegister cashRegister) {
        return cashRegisterRepository.save(cashRegister);
    }

    public CashRegister getCashRegister(Long cashRegisterId) {
        return cashRegisterRepository.findById(cashRegisterId).orElseThrow(
                () -> new ResourceNotFoundException("Cash register with id: " + cashRegisterId + " does not exist")
        );
    }

    public Float getCashRegisterTotal(Long cashRegisterId) {
        CashRegister cashRegister = getCashRegister(cashRegisterId);
        Float total = (float) 0;

        for (Receipt receipt : cashRegister.getReceipts()) {
            total += receipt.calculateReceiptTotal();
        }

        return total;
    }

    public Receipt createNewReceipt(Long cashRegisterId, Receipt receipt) {
        receipt.setCashRegister(getCashRegister(cashRegisterId));
        return receiptService.saveNewReceipt(receipt);
    }
}
