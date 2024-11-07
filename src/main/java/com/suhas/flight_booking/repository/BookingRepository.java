package com.suhas.flight_booking.repository;

import com.suhas.flight_booking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
