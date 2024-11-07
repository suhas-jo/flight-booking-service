package com.suhas.flight_booking.paymentstrategies;

import com.suhas.flight_booking.models.Seat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TotalAmountCalculationStrategy {

    public int getAmount(List<Seat> seatList){
        return 5000;
    }
}
