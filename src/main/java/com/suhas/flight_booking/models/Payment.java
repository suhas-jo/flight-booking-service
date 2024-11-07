package com.suhas.flight_booking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Payment extends BaseModel{

    private String refId;
    @Enumerated
    private PaymentProvider paymentProvide;
    @Enumerated
    private PaymentStatus paymentStatus;
    private Date paymentDate;
}
