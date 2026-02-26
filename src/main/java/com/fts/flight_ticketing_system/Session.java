package com.fts.flight_ticketing_system;

import java.util.UUID;

public class Session {
    private UUID id;

    public Session() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
