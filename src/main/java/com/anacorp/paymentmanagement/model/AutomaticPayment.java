package com.anacorp.paymentmanagement.model;

import com.anacorp.paymentmanagement.enums.TypePayment;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutomaticPayment extends Payment{

    private String scheduledDate;
    private String businessDocumentNumber;
    private TypePayment typePayment = TypePayment.AUTOMATIC;

}
