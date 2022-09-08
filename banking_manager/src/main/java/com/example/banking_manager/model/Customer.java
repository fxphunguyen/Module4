package com.example.banking_manager.model;

import groovy.transform.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    @Size(min = 5, max = 50, message = "The length of email must be between 5 and 50 characters.")
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String address;

    @Digits(integer = 9, fraction = 0)
    @Column(updatable = false)
    private BigDecimal balance;

}
