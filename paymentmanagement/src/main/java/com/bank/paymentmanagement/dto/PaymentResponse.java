package com.bank.paymentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {

    private String id;

    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant creationDate;

    private String description;

    private String status;



    private String personDocumentNumber;

    private String businessDocumentNumber;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant scheduledDate;

    @JsonIgnore
    public Instant getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public long getCreationDateEpoch() {
        return creationDate != null ? creationDate.getEpochSecond() : 0L;
    }

    @JsonIgnore
    public Instant getScheduledDate() {
        return scheduledDate;
    }

    @JsonProperty("scheduledDate")
    public Long getScheduledDateEpoch() {
        return scheduledDate != null ? scheduledDate.getEpochSecond() : null;
    }


}
