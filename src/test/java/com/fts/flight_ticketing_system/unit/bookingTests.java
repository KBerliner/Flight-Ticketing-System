package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Booking;
import com.fts.flight_ticketing_system.User;

public class bookingTests {
    @Test
    void shouldInitializeBooking() {
        Booking booking = new Booking();

        assertNotNull(booking.getId());
    }

    @Test
    void shouldInitializeBookings_WithDifferentIds() {
        Booking booking = new Booking();

        Booking booking2 = new Booking();

        assertNotEquals(booking.getId(), booking2.getId());
    }

    @Test
    void shouldContainUserId() throws DataFormatException {
        User user = new User(null, null, null, "email@gmail.com", "blablapassword");

        Booking booking = new Booking(user);

        UUID expected = user.getId();
        UUID actual = booking.getUserId();

        assertEquals(expected, actual);
    }
}
