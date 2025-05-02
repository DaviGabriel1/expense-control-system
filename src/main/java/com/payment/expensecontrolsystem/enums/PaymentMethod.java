package com.payment.expensecontrolsystem.enums;

public enum PaymentMethod {
    CASH("Dinheiro"), DEBIT("Débito"), CREDIT("Crédito"), PIX("pix"), OTHER("outro");

    private String name;

    PaymentMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
