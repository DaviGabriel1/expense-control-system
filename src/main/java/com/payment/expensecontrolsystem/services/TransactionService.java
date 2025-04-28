package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.transactions.TransactionRequestDTO;
import com.payment.expensecontrolsystem.exceptions.ResourceNotFoundException;
import com.payment.expensecontrolsystem.interfaces.ITransactionService;
import com.payment.expensecontrolsystem.models.Transactions;
import com.payment.expensecontrolsystem.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    public Transactions getTransactionByUuid(String uuid) {
        return this.transactionRepository.findByUuid(uuid).orElseThrow(() -> new ResourceNotFoundException("transaction not found"));
    }

    public Transactions[] getTransactionsbyUserId(Long id) {
        return new Transactions[0];
    }

    public Transactions createTransaction(Transactions transaction) {
        return this.transactionRepository.save(transaction);
    }

    public Transactions updatedTransaction(TransactionRequestDTO transaction, String uuid) {
        return null;
    }

    public void deleteTransaction(String uuid) {

    }
}
