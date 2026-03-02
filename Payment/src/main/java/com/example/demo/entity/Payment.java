package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private Long customerId;

    private Double amount;

    private String paymentMethod;  // CARD / UPI
    private String paymentStatus;  // SUCCESS / FAILED

    private String transactionId;

    private LocalDateTime paymentDate;
}