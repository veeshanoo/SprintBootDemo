package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRegisterRepository  extends JpaRepository<CashRegister, Long> {
}
