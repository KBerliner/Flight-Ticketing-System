package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Flight;

public class flightTests {
    @Test
    void shouldInitialize() {
        ZonedDateTime plannedDepartureExample = ZonedDateTime.now().plusDays(1);
        Duration plannedDurationExample = Duration.ofHours(2);
        ZonedDateTime plannedArrivalExample = plannedDepartureExample.plusHours(2);

        Flight flight = new Flight(10.0, plannedDepartureExample, plannedDurationExample);

        assertEquals(10, flight.getDistance());
        assertEquals(Flight.STATUS.UPCOMING, flight.getStatus());
        assertEquals(plannedDepartureExample, flight.getDeparture());
        assertEquals(plannedDurationExample, flight.getDuration());
        assertEquals(plannedArrivalExample, flight.getArrival());
    }

    @Test
    void getDepartureAndArrivalChange_WhenFlightGoesLong() {
        ZonedDateTime plannedDeparture = ZonedDateTime.now().minusHours(3);
        Duration plannedDuration = Duration.ofHours(2);
        ZonedDateTime plannedArrival = plannedDeparture.plus(plannedDuration);

        Flight flight = new Flight(10.0, plannedDeparture, plannedDuration);

        // Verifying that the duration has changed to be longer than the original planned duration
        assertTrue(flight.getDuration().compareTo(plannedDuration) > 0);
        
        // Verifying that the arrival is now set to later (DOES NOT PREDICT ARRIVAL TIME)
        assertTrue(flight.getArrival().compareTo(plannedArrival) > 0);
    }
}
