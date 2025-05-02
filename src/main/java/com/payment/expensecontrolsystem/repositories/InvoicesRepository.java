package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.models.Invoices;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {

    @Query("SELECT i FROM Invoices i LEFT JOIN FETCH i.products WHERE i.id = :id")
    Invoices findByIdWithProducts(@Param("id") Long id);

    @EntityGraph(attributePaths = "products")
    List<Invoices> findAll();

    @Query("SELECT i FROM Invoices i LEFT JOIN FETCH i.products WHERE i.createdAt >= :startDate AND i.createdAt <= :endDate")
    List<Invoices> findAllInInterval(Date startDate, Date endDate);

    @Query("SELECT i FROM Invoices i LEFT JOIN FETCH i.products WHERE i.createdAt >= :startDate")
    List<Invoices> findAllInIntervalByStartDate(Date startDate);

    @Query("SELECT i FROM Invoices i LEFT JOIN FETCH i.products WHERE i.createdAt <= :endDate")
    List<Invoices> findAllInIntervalByEndDate(Date endDate);
}
