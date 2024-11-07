package com.suhas.flight_booking.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {

    private List<Long> seatList;
    private Long userId;
    private Long flightId;
}
