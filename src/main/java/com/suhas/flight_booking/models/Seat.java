package com.suhas.flight_booking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Seat extends BaseModel{

    private String seatNum;
    @ManyToOne
    private SeatType seatType;
    @Enumerated
    private SeatStatus seatStatus;
    private Date bookedAt;

}
