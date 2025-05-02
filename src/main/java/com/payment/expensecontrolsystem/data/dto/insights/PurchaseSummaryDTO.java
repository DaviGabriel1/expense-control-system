package com.payment.expensecontrolsystem.data.dto.insights;

import com.payment.expensecontrolsystem.interfaces.IDto;

import java.math.BigDecimal;

public class PurchaseSummaryDTO implements IDto {
    private final BigDecimal totalSpent;
    private final Integer totalPurchases;
    private final BigDecimal averageTicketPrice;

    public PurchaseSummaryDTO(BigDecimal totalSpent, Integer totalPurchases, BigDecimal averageTicketPrice) {
        this.totalSpent = totalSpent;
        this.totalPurchases = totalPurchases;
        this.averageTicketPrice = averageTicketPrice;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public Integer getTotalPurchases() {
        return totalPurchases;
    }

    public BigDecimal getAverageTicketPrice() {
        return averageTicketPrice;
    }
}
