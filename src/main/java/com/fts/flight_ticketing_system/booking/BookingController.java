package com.fts.flight_ticketing_system.booking;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
