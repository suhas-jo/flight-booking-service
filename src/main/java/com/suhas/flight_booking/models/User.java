package com.suhas.flight_booking.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseModel{

    private String name;
    private String email;
    private String pwd;
}
