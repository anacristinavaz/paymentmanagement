package com.bank.paymentmanagement.util;

import com.bank.paymentmanagement.dto.PaymentRequest;
import com.bank.paymentmanagement.dto.PaymentResponse;
import com.bank.paymentmanagement.model.AutomaticPayment;
import com.bank.paymentmanagement.model.Payment;
import com.bank.paymentmanagement.model.SimplePayment;

import java.util.UUID;


public class PaymentMapper {

    public static Payment toEntity(PaymentRequest request) {
        if (request.getBusinessDocumentNumber() != null && request.getScheduledDate() != null) {
            AutomaticPayment payment = new AutomaticPayment();
            payment.setBusinessDocumentNumber(request.getBusinessDocumentNumber());
            payment.setScheduledDate(request.getScheduledDate());
            return populateCommon(payment, request);
        } else if (request.getPersonDocumentNumber() != null) {
            SimplePayment payment = new SimplePayment();
            payment.setPersonDocumentNumber(request.getPersonDocumentNumber());
            return populateCommon(payment, request);
        }
        return null;
    }

    private static <T extends Payment> T populateCommon(T payment, PaymentRequest request) {
        payment.setId(UUID.randomUUID().toString());
        payment.setAmount(request.getAmount());
        payment.setCreationDate(request.getCreationDate());
        payment.setDescription(request.getDescription());
        payment.setStatus(request.getStatus());
        return payment;
    }

    public static PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setCreationDate(payment.getCreationDate());
        response.setDescription(payment.getDescription());
        response.setStatus(payment.getStatus());

        if (payment instanceof SimplePayment simple) {
            response.setPersonDocumentNumber(simple.getPersonDocumentNumber());
        } else if (payment instanceof AutomaticPayment automatic) {
            response.setBusinessDocumentNumber(automatic.getBusinessDocumentNumber());
            response.setScheduledDate(automatic.getScheduledDate());
        }

        return response;
    }

    public static void updateEntityFromRequest(Payment original, PaymentRequest request) {
        if (request.getAmount() != null) original.setAmount(request.getAmount());
        if (request.getDescription() != null) original.setDescription(request.getDescription());
        if (request.getStatus() != null) original.setStatus(request.getStatus());

        if (original instanceof SimplePayment simple && request.getPersonDocumentNumber() != null) {
            simple.setPersonDocumentNumber(request.getPersonDocumentNumber());
        } else if (original instanceof AutomaticPayment automatic) {
            if (request.getBusinessDocumentNumber() != null)
                automatic.setBusinessDocumentNumber(request.getBusinessDocumentNumber());
            if (request.getScheduledDate() != null)
                automatic.setScheduledDate(request.getScheduledDate());
        }
    }

}
