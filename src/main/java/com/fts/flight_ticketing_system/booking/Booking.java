package com.fts.flight_ticketing_system.booking;

import java.util.HashMap;
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

    public HashMap<String, Object> getBookingAsHashMap() {
        HashMap<String, Object> bookingHashMap = new HashMap<>();
        bookingHashMap.put("id", id);
        bookingHashMap.put("userId", userId);
        bookingHashMap.put("flightId", flightId);

        return bookingHashMap;
    }
}
