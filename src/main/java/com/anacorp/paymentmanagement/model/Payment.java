package com.anacorp.paymentmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    private String id;
    private String amount;
    private String createdDate;
    private String status;
    private String description;

}
