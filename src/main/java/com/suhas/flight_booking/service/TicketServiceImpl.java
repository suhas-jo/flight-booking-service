package com.suhas.flight_booking.service;

import com.suhas.flight_booking.models.*;
import com.suhas.flight_booking.paymentstrategies.TotalAmountCalculationStrategy;
import com.suhas.flight_booking.repository.BookingRepository;
import com.suhas.flight_booking.repository.FlightRepository;
import com.suhas.flight_booking.repository.SeatRepository;
import com.suhas.flight_booking.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    UserRepository userRepository;
    FlightRepository flightRepository;
    SeatRepository seatRepository;
    BookingRepository bookingRepository;
    TotalAmountCalculationStrategy totalAmountCalculationStrategy;


    public TicketServiceImpl(UserRepository userRepository,
                             FlightRepository flightRepository,
                             SeatRepository seatRepository,
                             BookingRepository bookingRepository,
                             TotalAmountCalculationStrategy totalAmountCalculationStrategy) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
        this.totalAmountCalculationStrategy = totalAmountCalculationStrategy;
    }

    @Override
    public Booking bookTicket(Long userId, Long flightId, List<Long> seatIds) {

        /*
            1. Check whether user exists or not
            2. Check for flight availability.

            **** Transaction BEGIN **************
            3. Get all the selected seats.
            4. If seats are not Available then throw exception.
            5. Otherwise Block all the seats.
            6. Save the Seat status to DB.
            **** END TRANSACTION *************

            7. Create Booking object and save it to DB.
            8. return the Booking back to controller.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("User Not Found.."); // define specific exception.
        }

        User bookedBy = optionalUser.get();

        Optional<Flight> optionalFlight = flightRepository.findById(flightId);

        if(optionalFlight.isEmpty()){
            throw new RuntimeException("Flight not Found.."); //define specifinc exception.
        }

        Flight flight = optionalFlight.get();

        List<Seat> seatList = seatBooking(seatIds);
        flight.setSeatList(seatList);

        int amount = totalAmountCalculationStrategy.getAmount(seatList);

        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setBookedBy(bookedBy);
        booking.setBookingStatus(BookingStatus.INPROGRESS);
        booking.setFlight(flight);
        booking.setTotalAmount(amount);

        return bookingRepository.save(booking);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    private List<Seat> seatBooking(List<Long> seatIds) {

        List<Seat> seatList = seatRepository.findAllById(seatIds);

        if(seatIds.size() != seatList.size()){
            throw new RuntimeException("Seats not found..");
        }

        List<Seat> bookedSeats = new ArrayList<>();

        for(Seat seat : seatList){

            if( seat.getSeatStatus().equals(SeatStatus.AVAILABLE) ||
                    ((seat.getSeatStatus().equals(SeatStatus.BLOCKED) &&
                            Duration.between(seat.getBookedAt().toInstant(), LocalDate.now()).toMinutes() > 10  )
            )){
                seat.setSeatStatus(SeatStatus.BLOCKED);
                seat.setBookedAt(new Date());
                bookedSeats.add(seat);
            }else{
                throw new RuntimeException("something went wrong..");
            }
        }

        return seatRepository.saveAll(bookedSeats);
    }
}
