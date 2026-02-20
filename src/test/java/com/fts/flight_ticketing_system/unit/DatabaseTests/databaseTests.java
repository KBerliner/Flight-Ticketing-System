package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Database;

public class databaseTests {
    Database db;

    @BeforeEach
    void initializeDB() {
        db = new Database("Test Database");
    }

    @Test
    void shouldSuccessfullyInitializeDB() {
        assertEquals("Test Database", db.getName());
        assertNotNull(db.getTables());
        assertNotNull(db.getCreatedAt());
    }

    @Test
    void shouldCreateTable() {
        db.createTable("Test Table");

        assertEquals(1, db.getTables().size());
    }

    @Test
    void shouldDropTable() {
        db.createTable("Test Table");

        db.dropTable("Test Table");

        assertEquals(0, db.getTables().size());
    }
}
