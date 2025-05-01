package com.payment.expensecontrolsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.payment.expensecontrolsystem.enums.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer totalItens;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @Column
    private Date createdAt;

    @OneToMany(mappedBy = "invoices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;*/

    public Invoices(Integer totalItens, PaymentMethod paymentMethod, BigDecimal totalValue) {
        this.totalItens = totalItens;
        this.paymentMethod = paymentMethod;
        this.totalValue = totalValue;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> product) {
        this.products = product;
    }

    public Invoices() {}

    @Override
    public String toString() {
        return "Invoices{" +
                "id=" + id +
                ", totalItens=" + totalItens +
                ", paymentMethod=" + paymentMethod +
                ", totalValue=" + totalValue +
                ", createdAt=" + createdAt +
                '}';
    }
}