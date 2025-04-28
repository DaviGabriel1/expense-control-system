package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.interfaces.ITransactionService;
import com.payment.expensecontrolsystem.models.Transactions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public Transactions getTransactionByUuid(@RequestParam(name = "uuid") String uuid) {
        return this.transactionService.getTransactionByUuid(uuid);
    }

    @PostMapping
    public Transactions createTransaction(@RequestBody Transactions transaction){
        return this.transactionService.createTransaction(transaction);
    }

    @PutMapping
    public Transactions updateTransaction(@RequestBody Transactions transactions){
        return new Transactions();
    }


}
