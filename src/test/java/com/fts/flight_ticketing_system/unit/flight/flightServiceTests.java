package com.fts.flight_ticketing_system.unit.flight;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
