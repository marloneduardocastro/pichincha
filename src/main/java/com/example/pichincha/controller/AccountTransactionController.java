package com.example.pichincha.controller;

import com.example.pichincha.model.AccountTransaction;
import com.example.pichincha.service.AccountTransactionService;
import com.example.pichincha.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/account-transactions")
@Api(tags = "Account Transactions")
public class AccountTransactionController {
    private final AccountTransactionService accountTransactionService;
    private final TransactionService transactionService;

    @Autowired
    public AccountTransactionController(AccountTransactionService accountTransactionService, TransactionService transactionService) {
        this.accountTransactionService = accountTransactionService;
        this.transactionService = transactionService;
    }

    @GetMapping("/balance/{identificationNumber}")
    @ApiOperation("Obtener saldo de la cuenta")
    public ResponseEntity<Double> getAccountBalance(@PathVariable("identificationNumber") String identificationNumber) {
        Double balance = accountTransactionService.getAccountBalance(identificationNumber);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping("/transaction-count/{identificationNumber}")
    @ApiOperation("Obtener cantidad de transacciones de la cuenta")
    public ResponseEntity<Integer> getTransactionCount(@PathVariable("identificationNumber") String identificationNumber) {
        Integer transactionCount = accountTransactionService.getTransactionCount(identificationNumber);
        return new ResponseEntity<>(transactionCount, HttpStatus.OK);
    }

    @GetMapping("/transactions/{identificationNumber}")
    @ApiOperation("Obtener transacciones de un usuario")
    public ResponseEntity<List<AccountTransaction>> getTransactionsByUser(@PathVariable("identificationNumber") String identificationNumber) {
        List<AccountTransaction> transactions = accountTransactionService.getTransactionsByUser(identificationNumber);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transactions/{identificationNumber}/{startDate}/{endDate}")
    @ApiOperation("Obtener transacciones por rango de fechas")
    public ResponseEntity<List<AccountTransaction>> getTransactionsByDateRange(
            @PathVariable("identificationNumber") String identificationNumber,
            @PathVariable("startDate") String startDate,
            @PathVariable("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<AccountTransaction> transactions = accountTransactionService.getTransactionsByDateRange(identificationNumber, start, end);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Enviar una transacción de cuenta")
    public ResponseEntity<String> sendAccountTransaction(@RequestBody AccountTransaction accountTransaction) {
        transactionService.sendAccountTransaction(accountTransaction);
        return ResponseEntity.ok("AccountTransaction enviado exitosamente");
    }
}
