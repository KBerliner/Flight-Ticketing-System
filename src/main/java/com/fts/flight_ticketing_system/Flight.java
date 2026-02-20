package com.fts.flight_ticketing_system;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Flight {
    public enum STATUS {
        UPCOMING,
        EN_ROUTE,
        LANDED
    }

    private Integer miles;
    private ZonedDateTime departure;
    private Duration duration;

    public Flight(Integer miles, ZonedDateTime departure, Duration duration) {
        this.miles = miles;
        this.departure = departure;
        this.duration = duration;
    }

    public Integer getMiles() {
        return miles;
    }

    public STATUS getStatus() {
        return STATUS.UPCOMING;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public Duration getDuration() {
        return duration;
    }

    public ZonedDateTime getPlannedArrival() {
        return departure.plus(duration);
    }

}
