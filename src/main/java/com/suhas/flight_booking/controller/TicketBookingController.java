package com.suhas.flight_booking.controller;

import com.suhas.flight_booking.dtos.RequestDto;
import com.suhas.flight_booking.dtos.ResponseDto;
import com.suhas.flight_booking.dtos.ResponseStatus;
import com.suhas.flight_booking.models.Booking;
import com.suhas.flight_booking.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class TicketBookingController {


    TicketService ticketService;

    public TicketBookingController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping("/booking")
    public ResponseEntity<ResponseDto> bookTicket(@RequestBody RequestDto requestDto){
        Booking booking;
        ResponseDto responseDto = new ResponseDto();
        try{
            booking = ticketService.bookTicket(requestDto.getUserId(), requestDto.getFlightId(), requestDto.getSeatList());
            responseDto.setBookingId(booking.getId());
            responseDto.setResponseStatus(ResponseStatus.CONFIRMED);
            responseDto.setAmount(booking.getTotalAmount());
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
