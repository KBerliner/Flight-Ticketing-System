package com.fts.flight_ticketing_system.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.User;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;

public class User_DatabaseTests {
    @Test
    void userTableShouldBeCreatedWithUserClass() throws DataFormatException {
        Database db = new Database("Ticketing System");
        Table userTable = db.createTable("User");
        User newUser = new User("Username", "First", "Last", "email@gmail.com", "password");
        UUID rowId = newUser.getId();
        
        userTable.insertEntry(rowId, newUser.getUserAsHashMap());

        Row retrievedUser = userTable.readEntry(rowId);

        assertNotNull(retrievedUser);
        assertEquals(0, retrievedUser.getColumnValuesMap().get("distance"));
    }
}
