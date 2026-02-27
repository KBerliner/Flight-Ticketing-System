package com.fts.flight_ticketing_system.unit.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    private User user;
    private Flight flight;
    private Booking booking;

    @BeforeEach
    void setUp() throws DataFormatException {
        user = new User("Username", "First", "Last", "email@gmail.com", "1234");

        flight = new Flight(10.0, ZonedDateTime.now().plusHours(12), Duration.ofHours(2));

        booking = new Booking(user, flight);
    }

    @Test
    void shouldInitializeBooking() {
        assertNotNull(booking.getId());
    }

    @Test
    void shouldInitializeBookings_WithDifferentIds() {
        Booking booking2 = new Booking(user, flight);

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
    void shouldInitialize_AsNotCheckedIn() {
        assertFalse(booking.isCheckedIn());
    }

    @Test 
    void shouldAllowCheckIn_Within24HoursOfFlightDeparture() {
        booking.checkIn();

        assertTrue(booking.isCheckedIn());
    }

    @Test
    void shouldNOTAllowCheckIn_Before24HoursBeforeFlightDeparture() {
        Flight lateFlight = new Flight(10.0, ZonedDateTime.now().plusDays(2), Duration.ofHours(2));

        Booking lateBooking = new Booking(user, lateFlight);

        lateBooking.checkIn();

        assertFalse(lateBooking.isCheckedIn());
    }

    @Test
    void shouldNOTAllowCheckIn_AfterFlightHasDeparted() {
        Flight alreadyDepartedFlight = new Flight(10.0, ZonedDateTime.now().minusHours(1), Duration.ofHours(2));

        Booking alreadyDepartedBooking = new Booking(user, alreadyDepartedFlight);

        alreadyDepartedBooking.checkIn();

        assertFalse(alreadyDepartedBooking.isCheckedIn());
    }

    @Test
    void shouldAssignASeatOnCheckIn() {
        booking.checkIn();

        assertNotNull(booking.getSeat());
    }
}
