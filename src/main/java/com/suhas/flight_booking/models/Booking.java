package com.suhas.flight_booking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Booking extends BaseModel{

    @ManyToOne
    private Flight flight;
    @Enumerated
    private BookingStatus bookingStatus;
    @OneToMany
    private List<Payment> payment;
    private Date bookedAt;
    @ManyToOne
    private User bookedBy;

    private int totalAmount;

}
