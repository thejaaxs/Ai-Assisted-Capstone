package com.example.demo.controller;

import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment pay(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
}