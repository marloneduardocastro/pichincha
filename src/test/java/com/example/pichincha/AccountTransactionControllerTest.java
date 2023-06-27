package com.example.pichincha;

import com.example.pichincha.controller.AccountTransactionController;
import com.example.pichincha.model.AccountTransaction;
import com.example.pichincha.service.AccountTransactionService;
import com.example.pichincha.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AccountTransactionControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AccountTransactionService accountTransactionService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private AccountTransactionController accountTransactionController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountTransactionController).build();
    }

    @Test
    public void testGetAccountBalance() throws Exception {
        when(accountTransactionService.getAccountBalance("123456")).thenReturn(1000.0);
        mockMvc.perform(get("/account-transactions/balance/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1000.0)));
    }

    @Test
    public void testGetTransactionCount() throws Exception {
        when(accountTransactionService.getTransactionCount("123456")).thenReturn(5);
        mockMvc.perform(get("/account-transactions/transaction-count/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(5)));
    }

    @Test
    public void testGetTransactionsByUser() throws Exception {
        AccountTransaction transaction = new AccountTransaction();
        when(accountTransactionService.getTransactionsByUser("123456")).thenReturn(Collections.singletonList(transaction));
        mockMvc.perform(get("/account-transactions/transactions/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is(transaction)));
    }

    @Test
    public void testGetTransactionsByDateRange() throws Exception {
        AccountTransaction transaction = new AccountTransaction();
        when(accountTransactionService.getTransactionsByDateRange("123456", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-12-31"))).thenReturn(Collections.singletonList(transaction));
        mockMvc.perform(get("/account-transactions/transactions/123456/2023-01-01/2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is(transaction)));
    }

    @Test
    public void testSendAccountTransaction() throws Exception {
        AccountTransaction transaction = new AccountTransaction();
        mockMvc.perform(post("/account-transactions")
                                .content(asJsonString(transaction))
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