package com.fts.flight_ticketing_system.unit.flight;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.flight.FlightService;

public class flightServiceTests {
    FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
    }

    @Test
    void shouldGetNoFlights_FromEmptyDB() {
        List flights = flightService.getAllFlights();

        assertEquals(0, flights.size());
    }

    @Test
    void shouldAddFlightToDB() throws DataFormatException {
        Flight flight = new Flight(10.0, ZonedDateTime.now(), Duration.ofHours(2));

        flightService.createFlight(flight);

        List<HashMap<String, Object>> flights = flightService.getAllFlights();

        HashMap<String, Object> retrievedFlight = flights.get(0);

        assertEquals(1, flights.size());
        assertEquals(flight.getFlightAsHashMap(), retrievedFlight);
    }
}
