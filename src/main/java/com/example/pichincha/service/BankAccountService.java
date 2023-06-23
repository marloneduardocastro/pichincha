package com.example.pichincha.service;

import com.example.pichincha.model.BankAccount;
import com.example.pichincha.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public Optional<BankAccount> getBankAccountById(String id) {
        return bankAccountRepository.findById(id);
    }

    public List<BankAccount> getAllBankAccounts() {
        return (List<BankAccount>) bankAccountRepository.findAll();
    }

    public List<BankAccount> getBankAccountsByIdentificationNumber(String identificationNumber) {
        return bankAccountRepository.findByIdentificationNumber(identificationNumber);
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
