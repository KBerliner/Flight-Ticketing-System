package com.fts.flight_ticketing_system;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Booking {
    private UUID id;
    private UUID userId;
    private UUID flightId;

    public Booking(User user, Flight flight) {
        this.id = UUID.randomUUID();
        this.userId = user.getId();
        this.flightId = flight.getId();

        user.addMiles(flight.getDistance());
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
    
    public UUID getFlightId() {
        return flightId;
    }
}
