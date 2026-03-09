package com.fts.flight_ticketing_system.unit.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.booking.Booking;
import com.fts.flight_ticketing_system.booking.BookingService;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.user.User;

public class bookingServiceTests {
    BookingService bookingService;
    User user;
    Flight flight;

    @BeforeEach
    void setUp() throws DataFormatException {
        bookingService = new BookingService();
        user = new User("Username", "First", "Last", "email@gmail.com", "Password");
        flight = new Flight(10.0, ZonedDateTime.now().plusDays(1), Duration.ofHours(2));
    }

    @Test
    void shouldGetNoBookingsFromEmptyDB() {
        assertNotNull(bookingService.getAllBookings());
        assertEquals(0, bookingService.getAllBookings().size());
    }

    @Test
    void shouldAddABookingToDB() throws DataFormatException {
        Booking booking = new Booking(user, flight);

        bookingService.createBooking(booking);

        HashMap<String, Object> retrievedBooking = bookingService.getAllBookings().get(0);

        assertEquals(booking.getBookingAsHashMap(), retrievedBooking);
    }
}
