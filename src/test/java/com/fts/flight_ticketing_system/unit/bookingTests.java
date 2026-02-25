package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Booking;

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
}
