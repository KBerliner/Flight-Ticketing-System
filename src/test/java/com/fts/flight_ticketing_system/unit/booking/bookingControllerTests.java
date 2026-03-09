package com.fts.flight_ticketing_system.unit.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.booking.Booking;
import com.fts.flight_ticketing_system.booking.BookingController;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.user.User;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class bookingControllerTests {
    Database database;
    Table bookingTable;

    User user;
    Flight flight;
    Booking booking;

    @Autowired
    MockMvc MockMvc;

    @BeforeEach
    void setUp() throws DataFormatException {
        database = FlightTicketingSystemApplication.database;
        bookingTable = database.getTables().get("bookings");

        user = new User("Username", "First", "Last", "email@gmail.com", "Password");
        flight = new Flight(10.0, ZonedDateTime.now().plusDays(2), Duration.ofHours(4));
        booking = new Booking(user, flight);
    }

    @Test
    void shouldHitBookingcontroller() throws Exception {
        MockMvc.perform(get("/api/bookings/")).andExpect(status().isOk());
    }

    @Test
    void shouldGetBookingsFromDB() throws Exception {
        bookingTable.insertEntry(ROWTYPE.BOOKING, booking.getId(), booking);

        MockMvc.perform(get("/api/bookings/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(booking.getId().toString()));
    }
}
