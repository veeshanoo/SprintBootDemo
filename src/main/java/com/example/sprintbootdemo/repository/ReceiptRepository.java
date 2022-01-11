package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
