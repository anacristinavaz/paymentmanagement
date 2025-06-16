package com.bank.paymentmanagement.validator;

import com.bank.paymentmanagement.dto.PaymentRequest;
import com.bank.paymentmanagement.exception.InvalidRequestException;
import com.bank.paymentmanagement.model.AutomaticPayment;
import com.bank.paymentmanagement.model.Payment;
import com.bank.paymentmanagement.model.SimplePayment;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestValidator {

    public void validatePostRequest(PaymentRequest request, Payment payment) {
        if (payment instanceof SimplePayment) {
            if (request.getPersonDocumentNumber().length() != 11) {
                throw new InvalidRequestException("O campo 'personDocumentNumber' deve ter 11 caracteres");
            }
        } else if (payment instanceof AutomaticPayment) {
            if (request.getBusinessDocumentNumber().length() != 14) {
                throw new InvalidRequestException("O campo 'businessDocumentNumber' deve ter 14 caracteres.");
            }
            if (request.getScheduledDate().isBefore(payment.getCreationDate())) {
                throw new InvalidRequestException("O campo 'scheduledDate' é necessário para AutomaticPayment, e deve ser posterior a data de criação");
            }
        }

        if (request.getAmount() == null) {
            throw new InvalidRequestException("amount is required");
        } else if (request.getAmount().scale() > 2) {
                throw new InvalidRequestException("amount must have at most two decimal places");
        }

        if (request.getCreationDate() == null) {
            throw new InvalidRequestException("creationDate is required");
        }

        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new InvalidRequestException("description is required");
        }

        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new InvalidRequestException("status is required");
        }
    }

    public void validatePatchRequest(PaymentRequest request, Payment original) {
        if (request.getAmount() != null && request.getAmount().scale() > 2) {
            throw new InvalidRequestException("amount must have at most two decimal places");
        }

        if(request.getCreationDate() != null) {
            throw new InvalidRequestException("A data de criação não pode ser alterada");
        }

        if (original instanceof SimplePayment) {
            if (request.getPersonDocumentNumber() != null &&
                    request.getPersonDocumentNumber().length() != 11) {
                throw new InvalidRequestException("Invalid person document number");
            }
        } else if (original instanceof AutomaticPayment) {
            if (request.getBusinessDocumentNumber() != null &&
                    request.getBusinessDocumentNumber().length() != 14) {
                throw new InvalidRequestException("Invalid business document number");
            }
            if (request.getScheduledDate() != null){
                if(request.getScheduledDate().isBefore(original.getCreationDate())) {
                    throw new InvalidRequestException("Data agendamento deve ser maior que data criação");
                }
            }
        }
    }

}

