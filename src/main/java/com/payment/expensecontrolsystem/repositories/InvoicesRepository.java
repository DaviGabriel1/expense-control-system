package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {

    @Query("SELECT i FROM Invoices i LEFT JOIN FETCH i.products WHERE i.id = :id")
    Invoices findByIdWithProducts(@Param("id") Long id);
}
