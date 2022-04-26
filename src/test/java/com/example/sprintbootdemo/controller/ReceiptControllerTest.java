package com.example.sprintbootdemo.controller;

import com.example.sprintbootdemo.dto.ReceiptResponseBodyDto;
import com.example.sprintbootdemo.mapper.ReceiptMapper;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.service.ReceiptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ReceiptControllerTest {
    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @Test
    @DisplayName("testing total for receipt with no products")
    public void receiptDoesNotExistTest() {
        Receipt receipt = new Receipt();
        receipt.setReceiptProducts(new ArrayList<>());
        receipt.setReceiptId(1L);
        ReceiptResponseBodyDto dto = new ReceiptResponseBodyDto(1L, 1L, new ArrayList<>());

        when(receiptService.getReceipt(receipt.getReceiptId())).thenReturn(receipt);

        ResponseEntity<Float> response = receiptController.getReceiptTotal(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), (float) 0.0);
    }
}