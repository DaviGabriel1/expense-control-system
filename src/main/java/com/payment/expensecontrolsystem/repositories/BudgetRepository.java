package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
}
