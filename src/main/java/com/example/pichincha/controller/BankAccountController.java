package com.example.pichincha.controller;

import com.example.pichincha.model.BankAccount;
import com.example.pichincha.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable("id") String id) {
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountById(id);
        return bankAccount.map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping("/user/{identificationNumber}")
    public ResponseEntity<List<BankAccount>> getBankAccountsByUser(@PathVariable("identificationNumber") String identificationNumber) {
        List<BankAccount> bankAccounts = bankAccountService.getBankAccountsByIdentificationNumber(identificationNumber);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable("id") String id, @RequestBody BankAccount bankAccount) {
        Optional<BankAccount> existingBankAccount = bankAccountService.getBankAccountById(id);
        if (existingBankAccount.isPresent()) {
            bankAccount.setId(id);
            BankAccount updatedBankAccount = bankAccountService.updateBankAccount(bankAccount);
            return new ResponseEntity<>(updatedBankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable("id") String id) {
        Optional<BankAccount> existingBankAccount = bankAccountService.getBankAccountById(id);
        if (existingBankAccount.isPresent()) {
            bankAccountService.deleteBankAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
