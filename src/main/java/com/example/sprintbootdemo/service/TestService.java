package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.model.TestModel;
import com.example.sprintbootdemo.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestModel> getTests() {
        return testRepository.findAll();
    }

    public TestModel saveNewTest(TestModel test) {
        return testRepository.save(test);
    }
}
