package com.example.sprintbootdemo.integration;

import com.example.sprintbootdemo.model.Product;
import com.example.sprintbootdemo.model.Tax;
import com.example.sprintbootdemo.service.TaxService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaxControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaxService taxService;

    @WithMockUser(username = "admin", password = "admin")
    @Test
    public void testInsert() throws Exception {
        Tax tax = new Tax("TaxC", (float) 0.24);
        mvc.perform(post("/api/tax")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tax)))
                .andExpect(status().isCreated());

        mvc.perform(get("/api/tax/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
