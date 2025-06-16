package com.bank.paymentmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "automatic_payment")
public class AutomaticPayment extends Payment {

    @Column(nullable = false)
    private String businessDocumentNumber;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant scheduledDate;

}
