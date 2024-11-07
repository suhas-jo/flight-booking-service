package com.suhas.flight_booking.repository;

import com.suhas.flight_booking.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
