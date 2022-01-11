package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
