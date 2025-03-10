package com.anacorp.paymentmanagement.model;

import com.anacorp.paymentmanagement.enums.TypePayment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimplePayment extends Payment{

    private String personDocumentNumber;
    private TypePayment typePayment = SIMPLE;

}
