package com.example.demo.service;

import com.example.demo.entity.Booking;
import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getBookingsByCustomer(Long customerId);

    List<Booking> getBookingsByDealer(Long dealerId);

    Booking confirmBooking(Long id);
    
    Booking cancelBooking(Long bookingId);
    
    Booking getById(Long id);
}