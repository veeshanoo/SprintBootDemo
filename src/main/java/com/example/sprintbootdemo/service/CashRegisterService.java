package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.repository.CashRegisterRepository;
import org.springframework.stereotype.Service;

@Service
public class CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;

    public CashRegisterService(CashRegisterRepository cashRegisterRepository) {
        this.cashRegisterRepository = cashRegisterRepository;
    }
}
