package com.example.sprintbootdemo;

import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.repository.TaxRepository;
import com.example.sprintbootdemo.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprintBootDemoApplication implements CommandLineRunner {
    @Autowired
    private TaxService taxService;

    public static void main(String[] args) {
        SpringApplication.run(SprintBootDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tax taxA = new Tax(1L, "TaxA", (float) 0.24);
        Tax taxB = new Tax(2L, "TaxB", (float) 0.20);

        taxService.saveNewTax(taxA);
        taxService.saveNewTax(taxB);
    }
}
