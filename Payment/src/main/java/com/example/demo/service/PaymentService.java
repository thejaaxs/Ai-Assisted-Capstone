package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.feign.BookingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingClient bookingClient;

    public Payment processPayment(Payment payment) {

        // 1️⃣ Check Booking Exists
        bookingClient.getBookingById(payment.getBookingId());

        // 2️⃣ Dummy Payment Logic
        boolean success = new Random().nextBoolean();

        if (success) {
            payment.setPaymentStatus("SUCCESS");
            payment.setTransactionId("TXN" + System.currentTimeMillis());
            bookingClient.confirmBooking(payment.getBookingId());
        } else {
            payment.setPaymentStatus("FAILED");
        }

        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}