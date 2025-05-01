package com.payment.expensecontrolsystem.data.dto;

import java.math.BigDecimal;

public class CreateProductDTO {
    private final String name;
    private final BigDecimal totalPrice;
    private final BigDecimal unitPrice;
    private final Integer quantity;
    private final String code;
    private final String measure;

    public CreateProductDTO(String name, BigDecimal totalPrice, BigDecimal unitPrice, Integer quantity, String code, String measure) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.code = code;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }

    public String getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return "CreateProductDTO{" +
                "name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", code='" + code + '\'' +
                ", measure='" + measure + '\'' +
                '}';
    }
}
