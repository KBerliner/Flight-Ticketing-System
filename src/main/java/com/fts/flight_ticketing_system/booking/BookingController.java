package com.fts.flight_ticketing_system.booking;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.user.User;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    BookingService bookingService;

    public BookingController() {
        bookingService = new BookingService();
    }

    @GetMapping("/")
    public ResponseEntity<List<HashMap<String, Object>>> getAllBookings() {
        List<HashMap<String, Object>> bookings = bookingService.getAllBookings();

        return ResponseEntity.ok().body(bookings);
    }

    @PostMapping("/")
    public ResponseEntity createBooking(@RequestBody HashMap<String, String> bookingItems) throws DataFormatException {
        System.out.println(bookingItems);

        HashMap<String, Object> userAndFlight = bookingService.getUserAndFlight(UUID.fromString(bookingItems.get("userId")), UUID.fromString(bookingItems.get("flightId")));

        Booking booking = new Booking((User) userAndFlight.get("user"), (Flight) userAndFlight.get("flight"));

        bookingService.createBooking(booking);

        return ResponseEntity.status(201).build();
    }
}
