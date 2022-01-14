package com.example.sprintbootdemo.mapper;

import com.example.sprintbootdemo.dto.TaxRequestBodyDto;
import com.example.sprintbootdemo.model.Tax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaxMapperTest {
    @Test
    @DisplayName("Tax mapper test")
    void correctDtoConversion() {
        TaxRequestBodyDto taxDto = new TaxRequestBodyDto("TaxA", (float) 2.3);
        Tax expectedValue = new Tax("TaxA", (float) 2.3);

        Tax realValue = new TaxMapper().TaxRequestBodyDtoToTax(taxDto);

        assertNotNull(realValue);
        assertEquals(expectedValue.getTaxName(), realValue.getTaxName());
        assertEquals(expectedValue.getTaxPercentage(), realValue.getTaxPercentage());
    }
}