package com.example.sprintbootdemo.mapper;

import com.example.sprintbootdemo.dto.ReceiptProductResponseBodyDto;
import com.example.sprintbootdemo.model.ReceiptProduct;
import org.springframework.stereotype.Component;

@Component
public class ReceiptProductMapper {
    public ReceiptProductResponseBodyDto ReceiptProductToReceiptProductResponseBodyDto(ReceiptProduct receiptProduct) {
        return new ReceiptProductResponseBodyDto(
                receiptProduct.getReceiptProductId(),
                receiptProduct.getReceipt().getReceiptId(),
                receiptProduct.getProduct().getProductId(),
                receiptProduct.getReceiptProductQuantity()
        );
    }
}
