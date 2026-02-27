package com.fts.flight_ticketing_system.booking;

import java.util.UUID;

import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.user.User;

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
}
