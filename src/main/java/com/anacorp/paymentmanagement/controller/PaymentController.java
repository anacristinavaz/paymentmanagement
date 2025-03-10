package com.anacorp.paymentmanagement.controller;

import com.anacorp.paymentmanagement.enums.TypePayment;
import com.anacorp.paymentmanagement.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {


    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/payment")
    public Payment getAllPayments(@RequestHeader(required = false) TypePayment type){
        return null;
    }

    @GetMapping("/payment/{id}")
    public Payment getPaymentById(@RequestParam String id){

        return null;
    }

    @PostMapping("/payment")
    public Payment createPayment(@RequestBody Payment payment){
        return null;
    }

    @PatchMapping("/payment/{id}")
    public Payment updatePayment(@RequestParam String id, @RequestBody Payment payment){
        return null;
    }

    @DeleteMapping("/payment/{id}")
    private Payment deletePayment(@RequestParam String id){
        return null;
    }

}
