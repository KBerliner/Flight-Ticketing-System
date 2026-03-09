package com.fts.flight_ticketing_system.unit.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;


import com.fts.flight_ticketing_system.booking.BookingService;

public class bookingServiceTests {
    @Test
    void shouldGetAllBookings() {
        BookingService bookingService = new BookingService();

        assertNotNull(bookingService.getAllBookings());
        assertEquals(0, bookingService.getAllBookings().size());
    }
}
