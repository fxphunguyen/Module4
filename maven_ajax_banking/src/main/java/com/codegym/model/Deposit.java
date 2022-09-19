package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "deposits")
@Accessors(chain = true)
public class Deposit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "transaction_amount", precision = 12, nullable= false)
    private BigDecimal transactionAmount;

    public Deposit(Customer customer, BigDecimal transactionAmount) {
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }

}
