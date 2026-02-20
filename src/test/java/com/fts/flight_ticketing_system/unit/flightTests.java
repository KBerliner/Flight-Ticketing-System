package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Flight flight = new Flight(10, plannedDepartureExample, plannedDurationExample);

        assertEquals(10, flight.getMiles());
        assertEquals(Flight.STATUS.UPCOMING, flight.getStatus());
        assertEquals(plannedDepartureExample, flight.getDeparture());
        assertEquals(plannedDurationExample, flight.getDuration());
        assertEquals(plannedArrivalExample, flight.getPlannedArrival());
    }
}
