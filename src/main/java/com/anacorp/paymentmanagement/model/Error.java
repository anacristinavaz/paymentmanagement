package com.anacorp.paymentmanagement.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Error {

    private String error_code;
    private String error_description;

}
