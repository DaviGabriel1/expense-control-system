package com.payment.expensecontrolsystem.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private String description;

    @Column(precision = 20, scale = 2, nullable = false, name = "total_value")
    private Double totalValue;

    @Column(precision = 20, scale = 2, nullable = false, name = "accumulated_value")
    private Double accumulated_value;

    @Column
    private LocalDateTime term;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getAccumulated_value() {
        return accumulated_value;
    }

    public void setAccumulated_value(Double accumulated_value) {
        this.accumulated_value = accumulated_value;
    }

    public LocalDateTime getTerm() {
        return term;
    }

    public void setTerm(LocalDateTime term) {
        this.term = term;
    }
}
