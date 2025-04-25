package com.payment.expensecontrolsystem.models;

import com.payment.expensecontrolsystem.enums.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal mouthlyLimit;

    @Column(nullable = false)
    private Double progress;

    @Column(nullable = false)
    private LocalDateTime period;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getMouthlyLimit() {
        return mouthlyLimit;
    }

    public void setMouthlyLimit(BigDecimal mouthlyLimit) {
        this.mouthlyLimit = mouthlyLimit;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public LocalDateTime getPeriod() {
        return period;
    }

    public void setPeriod(LocalDateTime period) {
        this.period = period;
    }
}
