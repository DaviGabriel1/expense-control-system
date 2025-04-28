package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.transactions.TransactionRequestDTO;
import com.payment.expensecontrolsystem.models.Transactions;

public interface ITransactionService {
    Transactions getTransactionByUuid(String uuid);
    Transactions[] getTransactionsbyUserId(Long id);
    Transactions createTransaction(Transactions transaction);
    Transactions updatedTransaction(TransactionRequestDTO transaction, String uuid);
    void deleteTransaction(String uuid);
}