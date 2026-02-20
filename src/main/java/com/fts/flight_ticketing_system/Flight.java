package com.fts.flight_ticketing_system;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Flight {
    public enum STATUS {
        UPCOMING,
        EN_ROUTE,
        LANDED
    }

    private Double distance;
    private ZonedDateTime departure;
    private Duration duration;

    public Flight(Double distance, ZonedDateTime departure, Duration duration) {
        this.distance = distance;
        this.departure = departure;
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public STATUS getStatus() {
        return STATUS.UPCOMING;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public Duration getDuration() {
        ZonedDateTime now = ZonedDateTime.now();
        Duration passedFlightTime = Duration.between(departure, now);
        Duration lateDuration = Duration.ofHours(passedFlightTime.compareTo(duration));

        if (lateDuration.isPositive()) {

            return passedFlightTime;
        }
        return duration;
    }

    public ZonedDateTime getArrival() {
        return departure.plus(getDuration());
    }

}
