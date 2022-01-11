package com.example.sprintbootdemo.repository;

import com.example.sprintbootdemo.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestModel, Integer> {
}
