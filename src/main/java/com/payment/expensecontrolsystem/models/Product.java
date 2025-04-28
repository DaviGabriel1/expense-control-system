package com.payment.expensecontrolsystem.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "total_price")
    private BigDecimal totalprice;

    @Column(nullable = false, name = "unit_price")
    private BigDecimal unitprice;

    @Column
    private Integer quantity;

    @Column
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoices invoices;

    public Product(String name, BigDecimal totalprice, BigDecimal unitprice, Integer quantity, String code, Invoices invoices) {
        this.name = name;
        this.totalprice = totalprice;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.code = code;
        this.invoices = invoices;
    }

    public Product() {}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalprice=" + totalprice +
                ", unitprice=" + unitprice +
                ", quantity=" + quantity +
                ", code='" + code + '\'' +
                ", invoices=" + invoices +
                '}';
    }
}
