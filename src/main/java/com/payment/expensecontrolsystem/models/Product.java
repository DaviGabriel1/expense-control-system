package com.payment.expensecontrolsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product { //TODO: campo categoria selecionado com IA simples
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

    @Column(nullable = false)
    private String measure;

    @Column
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoices invoices;

    public Product(Long id, String name, BigDecimal totalprice, BigDecimal unitprice, Integer quantity, String code, String measure, Date createdAt, Invoices invoices) {
        this.id = id;
        this.name = name;
        this.totalprice = totalprice;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.code = code;
        this.measure = measure;
        this.createdAt = createdAt;
        this.invoices = invoices;
    }

    public Product() {}

    public Product(String nomeProduto, BigDecimal totalPrice, BigDecimal unitPrice, Integer quantity, String code, String measure, Invoices invoices) {
        this.name = nomeProduto;
        this.totalprice = totalPrice;
        this.unitprice = unitPrice;
        this.quantity = quantity;
        this.code = code;
        this.measure = measure;
        this.createdAt = new Date();
        this.invoices = invoices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Invoices getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

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
