package com.example.pichincha.repository;

import com.example.pichincha.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByUserIdentificationNumber(String identificationNumber);

    List<AccountTransaction> findByUserIdentificationNumberAndDateBetween(String identificationNumber, LocalDate startDate, LocalDate endDate);
    List<AccountTransaction> findByUserIdentificationNumberAndDateRange(String identificationNumber, LocalDate startDate, LocalDate endDate);
}
