package com.anacorp.paymentmanagement.service;

import com.anacorp.paymentmanagement.model.Filtro;
import com.anacorp.paymentmanagement.model.Payment;

import java.util.List;

public interface PaymentService {

    public Payment getPaymentsById(String id);
    public List<Payment> getAllPayments(Filtro filtro);
    public Payment createPayment(Payment payment);
    public Payment updatePayment(String id, Payment Payment);
    public void deletePayment(String id);

}
