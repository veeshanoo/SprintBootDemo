package com.example.sprintbootdemo.mapper;

import com.example.sprintbootdemo.dto.ReceiptProductResponseBodyDto;
import com.example.sprintbootdemo.dto.ReceiptResponseBodyDto;
import com.example.sprintbootdemo.model.Receipt;
import com.example.sprintbootdemo.model.ReceiptProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptMapper {
    @Autowired
    private ReceiptProductMapper receiptProductMapper;

    public ReceiptResponseBodyDto ReceiptToReceiptResponseBodyDto(Receipt receipt) {
        if (receipt.getReceiptProducts() == null) {
            receipt.setReceiptProducts(new ArrayList<>());
        }

        return new ReceiptResponseBodyDto(
                receipt.getReceiptId(),
                receipt.getCashRegister().getCashRegisterId(),
                receipt.getReceiptProducts().stream().map(
                        receiptProductMapper::ReceiptProductToReceiptProductResponseBodyDto)
                        .collect(Collectors.toList()));
    }
}
