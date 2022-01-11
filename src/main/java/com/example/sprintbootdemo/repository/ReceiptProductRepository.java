package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.ReceiptProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptProductRepository extends JpaRepository<ReceiptProduct, Long> {
}
