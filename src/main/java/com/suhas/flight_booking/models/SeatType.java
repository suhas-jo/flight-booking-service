package com.suhas.flight_booking.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class SeatType extends BaseModel{

    private String seatType;
}
