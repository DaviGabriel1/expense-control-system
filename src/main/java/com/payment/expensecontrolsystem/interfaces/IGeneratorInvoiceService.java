package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.invoices.CreateInvoiceProductsDTO;
import com.payment.expensecontrolsystem.models.Invoices;

public interface IGeneratorInvoiceService {
    void generateInvoice(String url) throws Exception;
    Invoices getInvoiceById(Long id) throws Exception;
    void createManualInvoice(CreateInvoiceProductsDTO createInvoiceProductsDTO);
}
