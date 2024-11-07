package com.suhas.flight_booking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Flight extends BaseModel{

    private String flightName;
    private String airlines;
    private Date departureTime;
    private Date arrivalTime;
    private String source;
    private String destination;
    @OneToMany
    private List<Seat> seatList;
}
