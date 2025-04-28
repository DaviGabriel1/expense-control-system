package com.payment.expensecontrolsystem.data.dto.transactions;

import com.payment.expensecontrolsystem.enums.Category;
import com.payment.expensecontrolsystem.enums.PaymentMethod;
import com.payment.expensecontrolsystem.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequestDTO {
    private final BigDecimal value;
    private final Category category;
    private final String description;
    private final LocalDateTime datetime;
    private final PaymentMethod paymentMethod;
    private final Status status;
    private final Long userId;

    public TransactionRequestDTO(BigDecimal value, Category category, String description, LocalDateTime datetime, PaymentMethod paymentMethod, Status status, Long userId) {
        this.value = value;
        this.category = category;
        this.description = description;
        this.datetime = datetime;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.userId = userId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }
}