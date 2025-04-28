package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.data.dto.transactions.TransactionRequestDTO;
import com.payment.expensecontrolsystem.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, String> {
    Optional<Transactions> findByUuid(String uuid);

    Transactions save(TransactionRequestDTO transaction);
}
