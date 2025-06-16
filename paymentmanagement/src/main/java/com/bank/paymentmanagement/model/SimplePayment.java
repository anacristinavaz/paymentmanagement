package com.bank.paymentmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "simple_payment")
public class SimplePayment extends Payment{

    @Column(nullable = false)
    private String personDocumentNumber;

}
