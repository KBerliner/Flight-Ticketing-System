package com.fts.flight_ticketing_system.unit.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.flight.FlightService;

public class flightServiceTests {
    FlightService flightService;
    Flight flight;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
        flight = new Flight(10.0, ZonedDateTime.now(), Duration.ofHours(2));
    }

    @Test
    void shouldGetNoFlights_FromEmptyDB() {
        List flights = flightService.getAllFlights();

        assertEquals(0, flights.size());
    }

    @Test
    void shouldAddFlightToDB() throws DataFormatException {
        flightService.createFlight(flight);

        List<HashMap<String, Object>> flights = flightService.getAllFlights();

        HashMap<String, Object> retrievedFlight = flights.get(0);

        assertEquals(1, flights.size());
        assertEquals(flight.getFlightAsHashMap(), retrievedFlight);
    }

    @Test
    void shouldRetrieveOneFlightFromDB() throws DataFormatException {
        flightService.createFlight(flight);

        Flight retrievedFlight = flightService.getFlight(flight.getId());

        List<HashMap<String, Object>> rows = flightService.getAllFlights();

        assertEquals(flight, retrievedFlight);
        assertEquals(1, rows.size());
    }

    @Test
    void shouldThrowNotFoundError_IfNoFlightExists() {
        assertThrows(NoSuchElementException.class, () -> {
            flightService.getFlight(null);
        });
    }

    @Test
    void shouldUpdateOneFlight() throws DataFormatException {
        flightService.createFlight(flight);

        HashMap<String, Object> updates = new HashMap<>();

        updates.put("duration", Duration.ofHours(3));

        flightService.updateFlight(flight.getId(), updates);

        Flight retrievedFlight = flightService.getFlight(flight.getId());

        assertEquals(Duration.ofHours(3), retrievedFlight.getDuration());
    }

    @Test
    void shouldDeleteFlight() throws DataFormatException {
        flightService.createFlight(flight);        

        assertEquals(1, flightService.getAllFlights().size());

        flightService.deleteFlight(flight.getId());

        assertEquals(0, flightService.getAllFlights().size());
    }

    @Test
    void shouldThrowNotFoundError_IfNoFlightExistsToDelete() {
        assertThrows(NoSuchElementException.class, () -> {
            flightService.deleteFlight(UUID.randomUUID());
        });
    }
}
