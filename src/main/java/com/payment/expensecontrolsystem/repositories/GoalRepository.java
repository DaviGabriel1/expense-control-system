package com.payment.expensecontrolsystem.repositories;

import com.payment.expensecontrolsystem.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, String> {
}
