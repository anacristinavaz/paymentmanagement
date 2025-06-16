package com.bank.paymentmanagement.service;

import com.bank.paymentmanagement.dto.PaymentResponse;
import com.bank.paymentmanagement.exception.InvalidRequestException;
import com.bank.paymentmanagement.exception.ResourceNotFoundException;
import com.bank.paymentmanagement.model.AutomaticPayment;
import com.bank.paymentmanagement.model.SimplePayment;
import com.bank.paymentmanagement.repository.AutomaticPaymentRepository;
import com.bank.paymentmanagement.repository.SimplePaymentRepository;
import com.bank.paymentmanagement.util.PaymentMapper;
import com.bank.paymentmanagement.validator.PaymentRequestValidator;
import com.bank.paymentmanagement.model.Payment;
import com.bank.paymentmanagement.dto.PaymentRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class PaymentService {

    private final SimplePaymentRepository simpleRepo;
    private final AutomaticPaymentRepository autoRepo;
    private final PaymentRequestValidator validator;

    public PaymentService(SimplePaymentRepository simpleRepo,
                          AutomaticPaymentRepository autoRepo,
                          PaymentRequestValidator validator) {
        this.simpleRepo = simpleRepo;
        this.autoRepo = autoRepo;
        this.validator = validator;
    }

    public PaymentResponse create(PaymentRequest request) {
        Payment payment = PaymentMapper.toEntity(request);
        if (payment == null) throw new InvalidRequestException("Invalid payment type");

        validator.validatePostRequest(request, payment);
        Payment saved = save(payment);
        return PaymentMapper.toResponse(saved);
    }

    public PaymentResponse update(String id, PaymentRequest request) {
        Optional<? extends Payment> existing = simpleRepo.findById(id);
        if (existing.isEmpty()) {
            existing = autoRepo.findById(id);
            if (existing.isEmpty()) throw new ResourceNotFoundException("Payment not found");
        }


        Payment original = existing.get();
        validator.validatePatchRequest(request, original);
        PaymentMapper.updateEntityFromRequest(original, request);
        Payment saved = save(original);

        return PaymentMapper.toResponse(saved);
    }

    public void delete(String id) {
        if (simpleRepo.existsById(id)) {
            simpleRepo.deleteById(id);
        } else if (autoRepo.existsById(id)) {
            autoRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Payment not found");
        }
    }

    public PaymentResponse findById(String id) {
        return simpleRepo.findById(id)
                .map(PaymentMapper::toResponse)
                .or(() -> autoRepo.findById(id).map(PaymentMapper::toResponse))
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }



    public List<PaymentResponse> findAll(Instant start, Instant end, String type) {
        List<Payment> payments = new ArrayList<>();

        boolean filterByDate;
            if (start != null && end != null) {
                long daysBetween = ChronoUnit.DAYS.between(start, end);
                if (daysBetween > 90) {
                    throw new InvalidRequestException("O intervalo entre as datas não pode exceder 90 dias.");
                } else {
                    filterByDate = true;
                }
            } else{
                filterByDate = false;
            }

        if (type == null || type.isBlank()) {
            List<SimplePayment> simples = filterByDate
                    ? simpleRepo.findByCreationDateBetween(start, end)
                    : simpleRepo.findAll();

            List<AutomaticPayment> automaticos = filterByDate
                    ? autoRepo.findByCreationDateBetween(start, end)
                    : autoRepo.findAll();

            payments.addAll(simples);
            payments.addAll(automaticos);
        } else if (type.equalsIgnoreCase("SIMPLE")) {
            payments.addAll(
                    filterByDate
                            ? simpleRepo.findByCreationDateBetween(start, end)
                            : simpleRepo.findAll()
            );
        } else if (type.equalsIgnoreCase("AUTOMATIC")) {
            payments.addAll(
                    filterByDate
                            ? autoRepo.findByCreationDateBetween(start, end)
                            : autoRepo.findAll()
            );
        } else {
            throw new InvalidRequestException("Tipo de pagamento inválido");
        }

        payments.sort(Comparator.comparing(Payment::getCreationDate).reversed());

        return payments.stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }


    private Payment save(Payment payment) {
        if (payment instanceof SimplePayment simple) return simpleRepo.save(simple);
        if (payment instanceof AutomaticPayment auto) return autoRepo.save(auto);
        throw new InvalidRequestException("Unknown payment type");
    }
}
