package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.interfaces.IInvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoices")
public class InvoicesController {
    private final IInvoiceService invoiceService;

    public InvoicesController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<String> getInvoices(@RequestParam(name = "url") String url, @RequestParam(name = "invoice_type") String invoiceType) throws Exception {
        switch (invoiceType) {
            case "nfce":
                this.invoiceService.generateInvoice(url);
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid invoice type");
        }
        return ResponseEntity.ok("ok");
    }
}
