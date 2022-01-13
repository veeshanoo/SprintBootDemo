package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findByTaxName(String taxName);
}
