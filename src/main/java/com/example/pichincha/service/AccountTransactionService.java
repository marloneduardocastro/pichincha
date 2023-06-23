package com.example.pichincha.service;

import com.example.pichincha.model.AccountTransaction;
import com.example.pichincha.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountTransactionService(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    public Double getAccountBalance(String identificationNumber) {
        List<AccountTransaction> transactions = accountTransactionRepository.findByUserIdentificationNumber(identificationNumber);
        double balance = 0.0;
        for (AccountTransaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public Integer getTransactionCount(String identificationNumber) {
        List<AccountTransaction> transactions = accountTransactionRepository.findByUserIdentificationNumber(identificationNumber);
        return transactions.size();
    }

    public List<AccountTransaction> getTransactionsByUser(String identificationNumber) {
        return accountTransactionRepository.findByUserIdentificationNumber(identificationNumber);
    }

    public List<AccountTransaction> getTransactionsByDateRange(String identificationNumber, LocalDate startDate, LocalDate endDate) {

        return accountTransactionRepository.findByUserIdentificationNumberAndDateRange(identificationNumber, startDate, endDate);
    }
}
