package com.example.sprintbootdemo;

import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.repository.AuthorityRepository;
import com.example.sprintbootdemo.repository.UserRepository;
import com.example.sprintbootdemo.security.Authority;
import com.example.sprintbootdemo.security.User;
import com.example.sprintbootdemo.service.CashRegisterService;
import com.example.sprintbootdemo.service.TaxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SprintBootDemoApplication implements CommandLineRunner {
    @Autowired
    private TaxService taxService;
    @Autowired
    private CashRegisterService cashRegisterService;

    @Autowired
    private Environment environment;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static void main(String[] args) {
        SpringApplication.run(SprintBootDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Active profiles: " +
                Arrays.toString(environment.getActiveProfiles()));

        Tax taxA = new Tax(1L, "TaxA", (float) 0.24);
        Tax taxB = new Tax(2L, "TaxB", (float) 0.20);
        taxService.saveNewTax(taxA);
        taxService.saveNewTax(taxB);

        CashRegister cashRegister1 = new CashRegister();
        CashRegister cashRegister2 = new CashRegister();
        cashRegisterService.saveNewCashRegister(cashRegister1);
        cashRegisterService.saveNewCashRegister(cashRegister2);

        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
            Authority userRole = authorityRepository.save(Authority.builder().role("ROLE_USER").build());
            User admin = User.builder().username("admin").password(passwordEncoder.encode("admin")).authority(adminRole).build();
            User user = User.builder().username("user").password(passwordEncoder.encode("user")).authority(userRole).build();
            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
