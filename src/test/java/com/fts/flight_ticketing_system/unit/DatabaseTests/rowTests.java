package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Rows.FlightRow;
import com.fts.flight_ticketing_system.database.Rows.Row;
import com.fts.flight_ticketing_system.database.Rows.RowFactory;
import com.fts.flight_ticketing_system.database.Rows.UserRow;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.user.User;

public class rowTests {
    private ROWTYPE type;
    private RowFactory rowFactory;
    private Row row;

    @BeforeEach
    void setUp() {
        rowFactory = new RowFactory();
    }

    @Test
    void shouldCorrectlyInitializeUserRow() throws DataFormatException {
        type = ROWTYPE.USER;

        User user = new User("Username", "First", "Last", "email@gmail.com", "Password");

        row = rowFactory.createRow(type, user.getId(), user);


        assertInstanceOf(UserRow.class, row);
        assertInstanceOf(Row.class, row);

        assertEquals(user.getId(), row.getRowId());
        assertEquals(user, row.getContent());

        assertNotNull(row.getCreatedAt());
        assertNotNull(row.getUpdatedAt());
    }

    @Test
    void shouldCorrectlyInitializeFlightRow() throws DataFormatException {
        type = ROWTYPE.FLIGHT;

        Flight flight = new Flight(10.0, ZonedDateTime.now(), Duration.ofHours(2));

        row = rowFactory.createRow(type, flight.getId(), flight);

        assertInstanceOf(FlightRow.class, row);
        assertInstanceOf(Row.class, row);

        assertEquals(flight.getId(), row.getRowId());
        assertEquals(flight, row.getContent());

        assertNotNull(row.getCreatedAt());
        assertNotNull(row.getUpdatedAt());
    }
}
