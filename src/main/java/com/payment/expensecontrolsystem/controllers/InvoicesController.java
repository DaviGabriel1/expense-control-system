package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.data.dto.invoices.CreateInvoiceProductsDTO;
import com.payment.expensecontrolsystem.interfaces.IFileManagementService;
import com.payment.expensecontrolsystem.interfaces.IGeneratorInvoiceService;
import com.payment.expensecontrolsystem.interfaces.IInvoiceService;
import com.payment.expensecontrolsystem.models.Invoices;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("invoices")
public class InvoicesController {
    private final IGeneratorInvoiceService generatorInvoiceService;
    private final IFileManagementService fileManagementService;
    private final IInvoiceService invoiceService;

    public InvoicesController(IGeneratorInvoiceService generatorInvoiceService, IFileManagementService fileManagementService, IInvoiceService invoiceService) {
        this.generatorInvoiceService = generatorInvoiceService;
        this.fileManagementService = fileManagementService;
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<String> createInvoices(@RequestParam(name = "url") String url, @RequestParam(name = "invoice_type") String invoiceType) throws Exception {
        switch (invoiceType) {
            case "nfce":
                this.generatorInvoiceService.generateInvoice(url);
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid invoice type");
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/manual")
    public ResponseEntity<String> createManualInvoices(@RequestBody CreateInvoiceProductsDTO invoiceProductsDTO, @RequestParam(name = "invoice_type") String invoiceType) throws Exception {
        this.generatorInvoiceService.createManualInvoice(invoiceProductsDTO);
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public Invoices getInvoiceById(@RequestParam(name = "id") Long id) throws Exception {
        return this.generatorInvoiceService.getInvoiceById(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadSpreadSheetsInvoice(
            @PathVariable(name = "id") Long invoiceId,
            @RequestParam(name = "dividedByCategory", defaultValue = "false") boolean dividedByCategory, //TODO: selecionar através de categorias, todas no mesmo sheet do excel caso a extensão seja xlsx
            @RequestParam(name = "file_name", defaultValue = "expense_control_spread_sheet") String fileName,
            @RequestParam(name = "file_extension", defaultValue = "xlsx") String fileExtension //TODO: fazer opções de download para o arquivo, como XML ou CSV, etc.
            ) throws Exception {
        Invoices invoices = this.generatorInvoiceService.getInvoiceById(invoiceId);
        File fileInvoices = this.fileManagementService.createInvoiceFileXlsx(fileName, invoices);
        Resource resource = new InputStreamResource(new FileInputStream(fileInvoices));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=\"" + fileInvoices.getName() + "\"")
                .body(resource);
    }

    @GetMapping("/download") //TODO: add userId ao criar a autenticação
    public ResponseEntity<Resource> downloadSpreadSheetsInvoices(
            @RequestParam(name = "dividedByCategory", defaultValue = "false") boolean dividedByCategory,
            @RequestParam(name = "file_name", defaultValue = "expense_control_spread_sheet") String fileName,
            @RequestParam(name = "file_extension", defaultValue = "xlsx") String fileExtension,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) throws FileNotFoundException {
        List<Invoices> invoices = this.invoiceService.findInvoicesByDate(startDate, endDate);
        File file = this.fileManagementService.CreateManyInvoicesFileXlsx(fileName,invoices);
        Resource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}
