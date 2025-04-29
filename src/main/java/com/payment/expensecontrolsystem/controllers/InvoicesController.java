package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.interfaces.IInvoiceService;
import com.payment.expensecontrolsystem.models.Invoices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("invoices")
public class InvoicesController {
    private final IInvoiceService invoiceService;

    public InvoicesController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<String> createInvoices(@RequestParam(name = "url") String url, @RequestParam(name = "invoice_type") String invoiceType) throws Exception {
        switch (invoiceType) {
            case "nfce":
                this.invoiceService.generateInvoice(url);
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid invoice type");
        }
        return ResponseEntity.ok("ok");
    }

    @GetMapping Invoices getInvoiceById(@RequestParam(name = "id") Long id) throws Exception {
        Invoices invoice = this.invoiceService.getInvoiceById(id);
        System.out.println(invoice.getProducts());
        return invoice;
    }
}
