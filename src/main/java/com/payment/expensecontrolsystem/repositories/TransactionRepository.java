package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, String> {
}
