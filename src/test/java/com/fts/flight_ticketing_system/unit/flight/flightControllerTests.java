package com.fts.flight_ticketing_system.unit.flight;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
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
    private JSONObject jObject;

    private Flight flight;

    @Autowired
    MockMvc MockMvc;

    @BeforeEach
    void setUp() {
        database = FlightTicketingSystemApplication.database;
        flightsTable = database.getTables().get("flights");

        flight = new Flight(10.0, ZonedDateTime.now().plusDays(1), Duration.ofHours(2));
    }

    @Test
    void shouldHitFlightController() throws Exception {
        MockMvc.perform(
            get("/api/flights/"))
        .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllFlights() throws Exception {
        flightsTable.insertEntry(ROWTYPE.FLIGHT, flight.getId(), flight);

        System.out.print(flight.getDuration());
    
        MockMvc.perform(
            get("/api/flights/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].status").value(Flight.STATUS.UPCOMING.toString()))
        .andExpect(jsonPath("$[0].duration").value(flight.getDuration().toString()));
    }

    @Test
    void shouldAddANewFlightToDB() throws Exception {
        HashMap<String, String> hashedFlight = new HashMap<>();
        hashedFlight.put("distance", flight.getDistance().toString());
        hashedFlight.put("departure", flight.getDeparture().toString());
        hashedFlight.put("duration", flight.getDuration().toString());

        jObject = new JSONObject(hashedFlight);
        String content = jObject.toJSONString();
        
        MockMvc.perform(
            post("/api/flights/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateAFlight() throws Exception {
        // Have to setup with a flight to update
        flightsTable.insertEntry(ROWTYPE.FLIGHT, flight.getId(), flight);

        ZonedDateTime oldArrivalTime = flight.getArrival();
        Duration oldDuration = flight.getDuration();
        Double oldDistance = flight.getDistance();
        ZonedDateTime oldDepartureTime = flight.getDeparture();

        HashMap<String, Object> updatesHashMap = new HashMap<>();
        updatesHashMap.put("distance", "15.0");
        updatesHashMap.put("duration", Duration.ofHours(5).toString());
        updatesHashMap.put("departure", ZonedDateTime.now().plusDays(1).plusHours(1).toString());

        jObject = new JSONObject(updatesHashMap);
        String content = jObject.toJSONString();

        MockMvc.perform(
            put("/api/flights/{id}", flight.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
        ).andExpect(status().isOk());

        Flight retrievedFlight = (Flight) flightsTable.readEntry(flight.getId()).getContent();
        Double newDistance = retrievedFlight.getDistance();
        ZonedDateTime newArrivalTime = retrievedFlight.getArrival();
        Duration newDuration = retrievedFlight.getDuration();
        ZonedDateTime newDepartureTime = retrievedFlight.getDeparture();

        assertNotEquals(oldDistance, newDistance);
        assertNotEquals(oldArrivalTime, newArrivalTime);
        assertNotEquals(oldDuration, newDuration);
        assertNotEquals(oldDepartureTime, newDepartureTime);
    }

    @Test
    void shouldDeleteAFlight() throws Exception {
        // Add a flight to delete
        flightsTable.insertEntry(ROWTYPE.FLIGHT, flight.getId(), flight);

        MockMvc.perform(delete("/api/flights/{id}", flight.getId())).andExpect(status().isOk());

        // Ensure flight is deleted
        assertNull(flightsTable.readEntry(flight.getId()));
    }

    @Test
    void shouldRespondWithBadRequestOnDelete_IfFlightDoesntExist() throws Exception {
        MockMvc.perform(delete("/api/flights/{id}", UUID.randomUUID())).andExpect(status().isBadRequest());
    }
}