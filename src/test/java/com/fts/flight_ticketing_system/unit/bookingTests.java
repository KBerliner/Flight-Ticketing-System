package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Booking;

public class bookingTests {
    @Test
    void shouldInitializeBooking() {
        Booking booking = new Booking();

        assertNotNull(booking.getId());
    }
}
