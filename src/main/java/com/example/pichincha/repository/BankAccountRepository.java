package com.example.pichincha.repository;

import com.example.pichincha.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, String> {
    List<BankAccount> findByIdentificationNumber(String identificationNumber);
}
