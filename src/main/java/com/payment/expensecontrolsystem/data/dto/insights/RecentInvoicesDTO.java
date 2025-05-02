package com.payment.expensecontrolsystem.data.dto.insights;

import com.payment.expensecontrolsystem.interfaces.IDto;
import com.payment.expensecontrolsystem.models.Invoices;

import java.util.List;

public class RecentInvoicesDTO implements IDto {
    private final List<Invoices> recentInvoices;

    public RecentInvoicesDTO(List<Invoices> recentInvoices) {
        this.recentInvoices = recentInvoices;
    }

    public List<Invoices> getRecentInvoices() {
        return recentInvoices;
    }
}
