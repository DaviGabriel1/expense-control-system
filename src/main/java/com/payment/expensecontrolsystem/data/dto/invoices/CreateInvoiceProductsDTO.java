package com.payment.expensecontrolsystem.data.dto.invoices;

import com.payment.expensecontrolsystem.data.dto.CreateProductDTO;
import com.payment.expensecontrolsystem.enums.PaymentMethod;
import java.util.List;

public class CreateInvoiceProductsDTO {
    private Integer totalItens;
    private PaymentMethod paymentMethod;
    private List<CreateProductDTO> products;

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<CreateProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<CreateProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CreateInvoiceProductsDTO{" +
                "totalItens=" + totalItens +
                ", paymentMethod=" + paymentMethod +
                ", products=" + products +
                '}';
    }
}
