package com.payment.expensecontrolsystem.models;

import com.payment.expensecontrolsystem.enums.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

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

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;*/

    public Invoices(Integer totalItens, PaymentMethod paymentMethod, BigDecimal totalValue) {
        this.totalItens = totalItens;
        this.paymentMethod = paymentMethod;
        this.totalValue = totalValue;
        this.createdAt = new Date();
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