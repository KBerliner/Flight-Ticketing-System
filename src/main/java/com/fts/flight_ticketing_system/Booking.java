package com.fts.flight_ticketing_system;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Booking {
    private UUID id;
    private UUID userId;
    private UUID flightId;
    private boolean checkedIn;
    private ZonedDateTime flightDeparture;

    // Constructor for if a user object is provided
    public Booking(User user, Flight flight) {
        this.id = UUID.randomUUID();
        this.userId = user.getId();
        this.flightId = flight.getId();
        this.flightDeparture = flight.getDeparture();

        this.checkedIn = false;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getFlightId() {
        return this.flightId;
    }
    
    public void checkIn() {
        if (flightDeparture.minusDays(1).isBefore(ZonedDateTime.now())) {
            this.checkedIn = true;
        }
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }
}
