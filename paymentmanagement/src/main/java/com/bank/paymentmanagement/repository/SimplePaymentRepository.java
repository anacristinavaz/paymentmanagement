package com.bank.paymentmanagement.repository;

import com.bank.paymentmanagement.model.SimplePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SimplePaymentRepository extends JpaRepository<SimplePayment, String> {

    List<SimplePayment> findByCreationDateBetween(Instant start, Instant end);

}
