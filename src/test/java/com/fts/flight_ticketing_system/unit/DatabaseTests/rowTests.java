package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Rows.Row;
import com.fts.flight_ticketing_system.database.Rows.RowFactory;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;
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
    void shouldCorrectlyInitialize() throws DataFormatException {
        type = ROWTYPE.USER;

        User user = new User("Username", "First", "Last", "email@gmail.com", "Password");

        row = rowFactory.createRow(type, user.getId(), user);


        assertEquals(user.getId(), row.getRowId());
        assertEquals(user, row.getContent());

        assertNotNull(row.getCreatedAt());
        assertNotNull(row.getUpdatedAt());
    }
}
