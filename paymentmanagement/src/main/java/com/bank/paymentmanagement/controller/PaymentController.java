package com.bank.paymentmanagement.controller;

import com.bank.paymentmanagement.dto.PaymentRequest;
import com.bank.paymentmanagement.dto.PaymentResponse;
import com.bank.paymentmanagement.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> update(@PathVariable String paymentId,
                                                  @RequestBody PaymentRequest request) {
        PaymentResponse response = service.update(paymentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> delete(@PathVariable String paymentId) {
        service.delete(paymentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable String paymentId) {
        return ResponseEntity.ok(service.findById(paymentId));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate end,
            @RequestParam(required = false) String type // SIMPLE ou AUTOMATIC
    ) {
        Instant startInstant = start != null ? start.atStartOfDay(ZoneOffset.UTC).toInstant() : null;
        Instant endInstant = end != null ? end.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant() : null;

        if (startInstant != null && endInstant != null) {
            long diffDays = Duration.between(startInstant, endInstant).toDays();
            if (diffDays > 90) {
                throw new IllegalArgumentException("Intervalo máximo permitido é de 90 dias");
            }
        }

        return ResponseEntity.ok(service.findAll(startInstant, endInstant, type));
    }

}


