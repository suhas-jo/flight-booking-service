package com.suhas.flight_booking.repository;

import com.suhas.flight_booking.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
