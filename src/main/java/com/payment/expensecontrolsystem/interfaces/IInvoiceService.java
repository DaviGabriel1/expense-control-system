package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.models.Invoices;

import java.util.Date;
import java.util.List;

public interface IInvoiceService {
    List<Invoices> findInvoicesByDate(Date startDate, Date endDate);
}
