package com.fts.flight_ticketing_system;

import java.util.UUID;

public class Booking {
    private UUID id;

    public Booking() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
    
}
