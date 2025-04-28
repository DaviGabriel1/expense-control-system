package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {

}
