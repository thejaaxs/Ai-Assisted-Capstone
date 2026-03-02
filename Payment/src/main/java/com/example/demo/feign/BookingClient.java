package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BOOKING")
public interface BookingClient {

    @GetMapping("/bookings/{id}")
    Object getBookingById(@PathVariable("id") Long id);

    @PutMapping("/bookings/confirm/{id}")
    Object confirmBooking(@PathVariable("id") Long id);
}