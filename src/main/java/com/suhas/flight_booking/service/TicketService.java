package com.suhas.flight_booking.service;

import com.suhas.flight_booking.models.Booking;

import java.util.List;

public interface TicketService {

    Booking bookTicket(Long userId, Long flightId, List<Long> seatIds);
}
