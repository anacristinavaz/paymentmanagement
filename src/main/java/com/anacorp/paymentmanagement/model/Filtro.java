package com.anacorp.paymentmanagement.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filtro {

    private String requestDate;
    private String typePayment;

}
