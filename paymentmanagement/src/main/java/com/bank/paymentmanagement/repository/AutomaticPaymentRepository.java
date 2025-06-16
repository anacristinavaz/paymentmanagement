package com.bank.paymentmanagement.repository;

import com.bank.paymentmanagement.model.AutomaticPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AutomaticPaymentRepository extends JpaRepository<AutomaticPayment, String> {

    List<AutomaticPayment> findByCreationDateBetween(Instant start, Instant end);

}
