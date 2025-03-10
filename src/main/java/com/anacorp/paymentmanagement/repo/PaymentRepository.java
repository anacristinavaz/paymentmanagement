package com.anacorp.paymentmanagement.repo;

import com.anacorp.paymentmanagement.model.Payment;
import org.springframework.data.repository.CrudRepository;

public abstract class PaymentRepository implements CrudRepository<Payment, Integer> {

}
