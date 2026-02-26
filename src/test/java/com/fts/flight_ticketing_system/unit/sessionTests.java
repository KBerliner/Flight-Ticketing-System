package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.Session;

public class sessionTests {
    @Test
    void shouldInitializeASession() {
        Session session = new Session();

        assertInstanceOf(UUID.class, session.getId());
    }
}
