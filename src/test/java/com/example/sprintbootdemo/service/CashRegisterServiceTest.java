package com.example.sprintbootdemo.service;

import com.example.sprintbootdemo.model.CashRegister;
import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.repository.CashRegisterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashRegisterServiceTest {
    @Mock
    private CashRegisterRepository cashRegisterRepository;

    @InjectMocks
    private CashRegisterService cashRegisterService;

    @Test
    @DisplayName("Has product returns true")
    void hasProduct() {
        CashRegister cashRegister = new CashRegister();
        cashRegister.setCashRegisterId(1L);
        Product product = new Product("test", (float) 1.2, null);
        product.setProductId(2L);
        List<Product> products = List.of(product);
        cashRegister.setProducts(products);

        when(cashRegisterRepository.findById(cashRegister.getCashRegisterId()))
                .thenReturn(Optional.of(cashRegister));

        boolean result = cashRegisterService.hasProduct(cashRegister.getCashRegisterId(), product.getProductId());

        assertTrue(result);

        verify(cashRegisterRepository).findById(cashRegister.getCashRegisterId());
    }
}