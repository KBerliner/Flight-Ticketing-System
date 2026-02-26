package com.fts.flight_ticketing_system;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Session {
    private UUID id;
    private ZonedDateTime expiration;

    public Session() {
        this.id = UUID.randomUUID();
        this.expiration = ZonedDateTime.now().plusMinutes(15);
    }

    public UUID getId() {
        return id;
    }

    public boolean isExpired() {
        return expiration.isBefore(ZonedDateTime.now());
    }

    // public ZonedDateTime getExpiration() {
    //     return expiration;
    // }
}
