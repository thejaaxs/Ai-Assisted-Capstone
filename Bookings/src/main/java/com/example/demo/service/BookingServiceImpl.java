package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {

        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingStatus("PENDING");
        booking.setPaymentStatus("UNPAID");
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsByDealer(Long dealerId) {
        return bookingRepository.findByDealerId(dealerId);
    }

    @Override
    public Booking cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingStatus("CANCELLED");

        return bookingRepository.save(booking);
        
        
    }
    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
    @Override
    public Booking confirmBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingStatus("CONFIRMED");
        booking.setPaymentStatus("PAID");

        return bookingRepository.save(booking);
    }
}