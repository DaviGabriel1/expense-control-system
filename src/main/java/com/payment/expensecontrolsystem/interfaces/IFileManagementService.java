package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.models.Invoices;

import java.io.File;
import java.util.List;

public interface IFileManagementService {
    File createInvoiceFileXlsx(String fileName, Invoices dto);
    File CreateManyInvoicesFileXlsx(String fileName, List<Invoices> invoices);
}
