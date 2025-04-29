package com.payment.expensecontrolsystem.data.dto.insights;

import com.payment.expensecontrolsystem.enums.PaymentMethod;

import java.util.List;
import java.util.Map;

public class PaymentMethodStatsDTO {
    private final List<Map<PaymentMethod, Integer>> paymentMethodStats;

    public PaymentMethodStatsDTO(List<Map<PaymentMethod, Integer>> paymentMethodStats) {
        this.paymentMethodStats = paymentMethodStats;
    }

    public List<Map<PaymentMethod, Integer>> getPaymentMethodStats() {
        return paymentMethodStats;
    }
}
