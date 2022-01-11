package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.model.TestModel;
import com.example.sprintbootdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("api/tests")
    public ResponseEntity<List<TestModel>> getTests() {
        return ResponseEntity.ok().body(testService.getTests());
    }

    @PostMapping("api/tests")
    public ResponseEntity<TestModel> saveTest(@RequestBody TestModel test) {
        return ResponseEntity.ok().body(testService.saveNewTest(test));
    }

}
