package com.kahan.service;

import com.kahan.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking findById(String bookingId);

    void updateBooking(Booking booking);

    List<String> getBookedSeats(int trainNo); // Returns seat numbers already booked
}
