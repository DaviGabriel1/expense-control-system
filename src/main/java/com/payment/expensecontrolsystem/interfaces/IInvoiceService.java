package com.payment.expensecontrolsystem.interfaces;

import java.io.IOException;

public interface IInvoiceService {
    void generateInvoice(String url) throws Exception;
}
