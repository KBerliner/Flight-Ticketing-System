package com.fts.flight_ticketing_system.unit.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.booking.Booking;
import com.fts.flight_ticketing_system.booking.BookingService;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.flight.FlightService;
import com.fts.flight_ticketing_system.user.User;
import com.fts.flight_ticketing_system.user.UserService;

public class bookingServiceTests {
    BookingService bookingService;
    UserService userService;
    FlightService flightService;
    User user;
    Flight flight;
    Booking booking;

    @BeforeEach
    void setUp() throws DataFormatException {
        bookingService = new BookingService();
        userService = new UserService();
        flightService = new FlightService();

        user = new User("Username", "First", "Last", "email@gmail.com", "Password");
        flight = new Flight(10.0, ZonedDateTime.now().plusDays(1), Duration.ofHours(2));
        booking = new Booking(user, flight);
    }

    @Test
    void shouldGetNoBookingsFromEmptyDB() {
        assertNotNull(bookingService.getAllBookings());
        assertEquals(0, bookingService.getAllBookings().size());
    }

    @Test
    void shouldAddABookingToDB() throws DataFormatException {
        bookingService.createBooking(booking);

        HashMap<String, Object> retrievedBooking = bookingService.getAllBookings().get(0);

        assertEquals(booking.getBookingAsHashMap(), retrievedBooking);
    }

    @Test
    void shouldGetOneBooking() throws DataFormatException {
        bookingService.createBooking(booking);

        Booking retrievedBooking = bookingService.getBooking(booking.getId());

        assertEquals(booking, retrievedBooking);
    }

    @Test
    void shouldDeleteBooking() throws DataFormatException {
        // Creating Booking to delete
        bookingService.createBooking(booking);
        assertEquals(booking, bookingService.getBooking(booking.getId()));

        bookingService.deleteBooking(booking.getId());
        assertEquals(0, bookingService.getAllBookings().size());
    }

    @Test
    void shouldThrowNotFoundError_IfNoBookingExistsToDelete() {
        assertThrows(NoSuchElementException.class, () -> {
            bookingService.deleteBooking(UUID.randomUUID());
        });
    }

    @Test
    void shouldGetUserAndFlightObjects() throws DataFormatException {
        // Setting up user and flight in database
        userService.createUser(user);
        flightService.createFlight(flight);

        HashMap<String, Object> result = bookingService.getUserAndFlight(user.getId(), flight.getId());

        assertInstanceOf(User.class, result.get("user"));
        assertInstanceOf(Flight.class, result.get("flight"));
    }

    @Test
    void shouldGetAllOfOneUsersBookings_ById() throws DataFormatException {
        userService.createUser(user);
        
        // Creating new flights to book the user on
        Flight secondFlight = new Flight(23.2, ZonedDateTime.now().plusMonths(1).plusDays(2), Duration.ofHours(4));
        Flight thirdFlight = new Flight(52.9, ZonedDateTime.now().plusYears(1), Duration.ofHours(7));

        // Creating Bookings
        Booking secondBooking = new Booking(user, secondFlight);
        Booking thirdBooking = new Booking(user, thirdFlight);

        bookingService.createBooking(booking);
        bookingService.createBooking(secondBooking);
        bookingService.createBooking(thirdBooking);

        // Retrieving Bookings
        List<Booking> bookings = bookingService.getAllBookingsForUser(user.getId());

        assertEquals(3, bookings.size());
    }
}
