package com.payment.expensecontrolsystem.mapper;

import com.payment.expensecontrolsystem.data.dto.invoices.CreateInvoiceProductsDTO;
import com.payment.expensecontrolsystem.models.Invoices;
import com.payment.expensecontrolsystem.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class InvoiceMapper {
    public static Invoices toEntity(CreateInvoiceProductsDTO invoice) {
        AtomicReference<BigDecimal> totalValue = new AtomicReference<>(BigDecimal.ZERO);
        Invoices invoiceEntity = new Invoices();
        invoiceEntity.setTotalItens(invoice.getTotalItens());
        invoiceEntity.setPaymentMethod(invoice.getPaymentMethod());
        List<Product> products = invoice.getProducts().stream().map(productDTO -> {
            totalValue.set(totalValue.get().add(productDTO.getTotalPrice()));
            Product productEntity = new Product();
            productEntity.setName(productDTO.getName());
            productEntity.setQuantity(productDTO.getQuantity());
            productEntity.setTotalprice(productDTO.getTotalPrice());
            productEntity.setUnitprice(productDTO.getUnitPrice());
            productEntity.setCode(productDTO.getCode());
            productEntity.setMeasure(productDTO.getMeasure());
            productEntity.setInvoices(invoiceEntity);
            return productEntity;
        }).toList();
        invoiceEntity.setTotalValue(new BigDecimal(totalValue.get().toString()));
        invoiceEntity.setProducts(products);
        return invoiceEntity;
    }
}
