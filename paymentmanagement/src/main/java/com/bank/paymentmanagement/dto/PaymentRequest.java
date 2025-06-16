package com.bank.paymentmanagement.dto;

import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class PaymentRequest {

    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant creationDate;

    private String description;

    private String status;


    private String personDocumentNumber;

    private String businessDocumentNumber;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant scheduledDate;


}
