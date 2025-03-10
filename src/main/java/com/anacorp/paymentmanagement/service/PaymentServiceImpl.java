package com.anacorp.paymentmanagement.service;

import com.anacorp.paymentmanagement.model.Filtro;
import com.anacorp.paymentmanagement.model.Payment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentServiceImpl implements PaymentService{


    @Override
    public Payment getPaymentsById(String id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments(Filtro filtro) {
        return List.of();
    }

    @Override
    public Payment createPayment(Payment payment) {
        return null;
    }

    @Override
    public Payment updatePayment(String id, Payment payment) {
        return null;
    }

    @Override
    public void deletePayment(String id) {

    }
}
