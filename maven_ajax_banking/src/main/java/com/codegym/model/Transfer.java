package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer")
@Accessors(chain = true)
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Customer recipient;

    @Column(name = "transfer_amount", precision = 12, nullable= false)
    private BigDecimal transferAmount;

    @Column(nullable= false)
    private Long fees;

    @Column(name = "fees_amount", precision = 12, nullable= false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, nullable= false)
    private BigDecimal transactionAmount;


    public Transfer(Customer sender, Customer recipient, BigDecimal transferAmount, Long fees, BigDecimal transactionAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.transactionAmount = transactionAmount;
    }
}
