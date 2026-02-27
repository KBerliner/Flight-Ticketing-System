package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Booking;
import com.fts.flight_ticketing_system.Flight;
import com.fts.flight_ticketing_system.User;

public class bookingTests {
    Booking booking;
    User user;
    Flight flight;

    @BeforeEach
    void setUp() throws DataFormatException {
        user = new User("Username", "First", "Last", "email@gmail.com", "1234");
        flight = new Flight(10.0, ZonedDateTime.now().plusDays(1), Duration.ofHours(2));

        booking = new Booking(user, flight);
    }

    @Test
    void shouldInitializeBooking() {
        assertNotNull(booking.getId());
    }

    @Test
    void shouldInitializeBookings_WithDifferentIds() {
        Flight newFlight = new Flight(15.0, ZonedDateTime.now().plusDays(2), Duration.ofHours(3));

        Booking booking2 = new Booking(user, newFlight);

        assertNotEquals(booking.getId(), booking2.getId());
    }

    @Test
    void shouldContainUserId() {
        UUID expected = user.getId();
        UUID actual = booking.getUserId();

        assertEquals(expected, actual);
    }

    @Test
    void shouldContainFlightId() {
        UUID expected = flight.getId();
        UUID actual = booking.getFlightId();

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddMilesToUser_WhenBookingIsCreated() {
        Double miles = user.getMiles();

        assertEquals(miles, 10.0);
    }
}
