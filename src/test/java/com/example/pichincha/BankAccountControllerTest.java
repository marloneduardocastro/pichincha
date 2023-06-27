package com.example.pichincha;

import com.example.pichincha.controller.BankAccountController;
import com.example.pichincha.model.BankAccount;
import com.example.pichincha.service.BankAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class BankAccountControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BankAccountService bankAccountService;

    @InjectMocks
    private BankAccountController bankAccountController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }

    @Test
    public void testCreateBankAccount() throws Exception {
        BankAccount bankAccount = new BankAccount();
        when(bankAccountService.createBankAccount(any(BankAccount.class))).thenReturn(bankAccount);

        mockMvc.perform(post("/bank-accounts")
                                .content(asJsonString(bankAccount))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetBankAccountById() throws Exception {
        BankAccount bankAccount = new BankAccount();
        when(bankAccountService.getBankAccountById("123")).thenReturn(Optional.of(bankAccount));

        mockMvc.perform(get("/bank-accounts/123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllBankAccounts() throws Exception {
        BankAccount bankAccount = new BankAccount();
        when(bankAccountService.getAllBankAccounts()).thenReturn(Arrays.asList(bankAccount));

        mockMvc.perform(get("/bank-accounts"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBankAccountsByUser() throws Exception {
        BankAccount bankAccount = new BankAccount();
        when(bankAccountService.getBankAccountsByIdentificationNumber("123456")).thenReturn(Arrays.asList(bankAccount));

        mockMvc.perform(get("/bank-accounts/user/123456"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBankAccount() throws Exception {
        BankAccount bankAccount = new BankAccount();
        when(bankAccountService.getBankAccountById("123")).thenReturn(Optional.of(bankAccount));
        when(bankAccountService.updateBankAccount(any(BankAccount.class))).thenReturn(bankAccount);

        mockMvc.perform(put("/bank-accounts/123")
                                .content(asJsonString(bankAccount))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBankAccount() throws Exception {
        BankAccount bankAccount = new BankAccount();
        when(bankAccountService.getBankAccountById("123")).thenReturn(Optional.of(bankAccount));

        mockMvc.perform(delete("/bank-accounts/123"))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

