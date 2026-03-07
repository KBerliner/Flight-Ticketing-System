package com.fts.flight_ticketing_system.unit.flight;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.flight.FlightController;

@WebMvcTest(FlightController.class)
@AutoConfigureMockMvc
public class flightControllerTests {
    private Database database;
    private Table flightsTable;

    private Flight flight;

    @Autowired
    MockMvc MockMvc;

    @BeforeEach
    void setUp() {
        database = FlightTicketingSystemApplication.database;
        flightsTable = database.getTables().get("flights");

        flight = new Flight(10.0, ZonedDateTime.now().minusDays(1), Duration.ofHours(2));
    }

    @Test
    void shouldHitFlightController() throws Exception {
        MockMvc.perform(get("/api/flights/")).andExpect(status().isOk());
    }

    @Test
    void shouldGetAllFlights() throws Exception {
        flightsTable.insertEntry(ROWTYPE.FLIGHT, flight.getId(), flight);
    
        MockMvc.perform(get("/api/flights/")).andExpect(status().isOk()).andExpect(jsonPath("$[0].status").value(Flight.STATUS.UPCOMING)).andExpect(jsonPath("$[0].status").exists());
    }
}