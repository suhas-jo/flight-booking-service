package com.suhas.flight_booking.dtos;

import lombok.Data;

@Data
public class UserRequestDto {

    private String userName;
    private String email;
    private String pwd;
}
