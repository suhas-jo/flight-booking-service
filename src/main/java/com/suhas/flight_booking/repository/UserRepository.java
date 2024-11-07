package com.suhas.flight_booking.repository;

import com.suhas.flight_booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
