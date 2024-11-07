package com.suhas.flight_booking.dtos;

import lombok.Data;

@Data
public class ResponseDto {

    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;
}
