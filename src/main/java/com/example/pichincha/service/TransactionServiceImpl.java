package com.example.pichincha.service;

import com.example.pichincha.model.AccountTransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    @Value("${account.transaction.url}")
    private String accountTransactionUrl;
    private RestTemplate restTemplate;
    @Override
    public void sendAccountTransaction(AccountTransaction accountTransaction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AccountTransaction> request = new HttpEntity<>(accountTransaction, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(accountTransactionUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("AccountTransaction guardado exitosamente");
        } else {
            System.out.println("Error al guardar AccountTransaction");
        }
    }
}
