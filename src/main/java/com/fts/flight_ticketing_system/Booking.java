package com.fts.flight_ticketing_system;

import java.util.UUID;

public class Booking {
    private UUID id;
    private UUID userId;

    // Constructor for if a user object is provided
    public Booking(User user) {
        this.id = UUID.randomUUID();
        this.userId = user.getId();
    }

    // Constructor for if a user object is NOT provided
    public Booking() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }
    
}
