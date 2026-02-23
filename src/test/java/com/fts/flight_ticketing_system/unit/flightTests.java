package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Flight;

public class flightTests {
    Flight flight;
    ZonedDateTime plannedDeparture = ZonedDateTime.now().plusDays(1);
    Duration plannedDuration = Duration.ofHours(2);

    @BeforeEach
    void setUp() {

        flight = new Flight(10.0, plannedDeparture, plannedDuration);
    }

    @AfterEach
    void cleanUp() {
        flight = null;
    }

    @Test
    void shouldInitialize() {
        ZonedDateTime plannedArrivalExample = flight.getDeparture().plusHours(2);

        assertEquals(10, flight.getDistance());
        assertEquals(Flight.STATUS.UPCOMING, flight.getStatus());
        assertEquals(Duration.ofHours(2), flight.getDuration());
        assertEquals(plannedArrivalExample, flight.getArrival());
    }

    @Test
    void getDepartureAndArrivalChange_WhenFlightGoesLong() {
        ZonedDateTime plannedDeparture = ZonedDateTime.now().minusHours(3);
        Duration plannedDuration = Duration.ofHours(2);
        ZonedDateTime plannedArrival = plannedDeparture.plus(plannedDuration);

        flight = new Flight(10.0, plannedDeparture, plannedDuration);

        // Verifying that the duration has changed to be longer than the original planned duration
        assertTrue(flight.getDuration().compareTo(plannedDuration) > 0);
        
        // Verifying that the arrival is now set to later (DOES NOT PREDICT ARRIVAL TIME)
        assertTrue(flight.getArrival().compareTo(plannedArrival) > 0);
    }

    @Test
    void FlightTakeoff_UpdatesStatus() {
        flight.takeOff();

        assertEquals(Flight.STATUS.EN_ROUTE, flight.getStatus());
    }

    @Test
    void FlightLanding_UpdatesStatus() {
        flight.takeOff();

        flight.land();

        assertEquals(Flight.STATUS.LANDED, flight.getStatus());
    }

    @Test
    void FlightTakeoff_UpdatesDeparture() {
        flight.takeOff();

        assertNotEquals(plannedDeparture, flight.getDeparture());
    }

    @Test
    void FlightLanding_UpdatesDurationAndArrival() {
        flight.takeOff();

        flight.land();

        assertNotEquals(plannedDuration, flight.getDuration());

        assertNotEquals(plannedDeparture.plus(plannedDuration), flight.getArrival());
    }
}
