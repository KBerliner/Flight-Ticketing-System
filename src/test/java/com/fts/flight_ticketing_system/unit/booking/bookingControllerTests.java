package com.fts.flight_ticketing_system.unit.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.booking.Booking;
import com.fts.flight_ticketing_system.booking.BookingController;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.flight.FlightService;
import com.fts.flight_ticketing_system.user.User;
import com.fts.flight_ticketing_system.user.UserService;

import net.minidev.json.JSONObject;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class bookingControllerTests {
    Database database;
    Table bookingTable;

    UserService userService;
    FlightService flightsService;

    User user;
    Flight flight;
    Booking booking;

    JSONObject jObject;

    @Autowired
    MockMvc MockMvc;

    @BeforeEach
    void setUp() throws DataFormatException {
        database = FlightTicketingSystemApplication.database;
        bookingTable = database.getTables().get("bookings");

        userService = new UserService();
        flightsService = new FlightService();

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

    @Test
    void shouldCreateABookingInDB() throws Exception {
        // Add user and flight to DB to make booking
        userService.createUser(user);
        flightsService.createFlight(flight);

        HashMap<String, String> bookingItems = new HashMap<>();
        bookingItems.put("userId", user.getId().toString());
        bookingItems.put("flightId", flight.getId().toString());

        jObject = new JSONObject(bookingItems);
        String content = jObject.toJSONString();

        MockMvc.perform(
            post("/api/bookings/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
        ).andExpect(status().isCreated());
    }
}
