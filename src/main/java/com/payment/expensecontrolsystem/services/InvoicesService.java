package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.interfaces.IInvoiceService;
import com.payment.expensecontrolsystem.models.Invoices;
import com.payment.expensecontrolsystem.repositories.InvoicesRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvoicesService implements IInvoiceService {
    private final InvoicesRepository invoicesRepository;

    public InvoicesService(InvoicesRepository invoiceRepository){
        this.invoicesRepository = invoiceRepository;
    }
    @Override
    public List<Invoices> findInvoicesByDate(Date startDate, Date endDate) { //TODO: paginação para o front-end
        if(startDate == null && endDate == null) {
            return this.invoicesRepository.findAll();
        }
        else if(startDate != null && endDate == null) {
            return this.invoicesRepository.findAllInIntervalByStartDate(startDate);
        }
        else if(startDate == null) {
            return this.invoicesRepository.findAllInIntervalByEndDate(endDate);
        }
        else {
            return this.invoicesRepository.findAllInInterval(startDate,endDate);
        }
    }
}
