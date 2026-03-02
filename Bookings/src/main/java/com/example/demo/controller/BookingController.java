package com.example.demo.controller;

import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/customer/{customerId}")
    public List<Booking> getByCustomer(@PathVariable Long customerId) {
        return bookingService.getBookingsByCustomer(customerId);
    }

    @GetMapping("/dealer/{dealerId}")
    public List<Booking> getByDealer(@PathVariable Long dealerId) {
        return bookingService.getBookingsByDealer(dealerId);
    }

    @PutMapping("/cancel/{bookingId}")
    public Booking cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @PutMapping("/confirm/{id}")
    public Booking confirmBooking(@PathVariable Long id) {
        return bookingService.confirmBooking(id);
    }
    
    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id) {
        return bookingService.getById(id);
    }
}