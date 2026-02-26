package com.fts.flight_ticketing_system;

import java.util.UUID;

public class Booking {
    private UUID id;
    private UUID userId;
    private UUID flightId;

    // Constructor for if a user object is provided
    public Booking(User user, Flight flight) {
        this.id = UUID.randomUUID();
        this.userId = user.getId();
        this.flightId = flight.getId();
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
    
}
