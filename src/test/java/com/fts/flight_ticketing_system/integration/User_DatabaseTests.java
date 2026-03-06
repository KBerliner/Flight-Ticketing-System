package com.fts.flight_ticketing_system.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.user.User;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;

public class User_DatabaseTests {
    @Test
    void userTableShouldBeCreatedWithUserClass() throws DataFormatException {
        Database db = new Database("Ticketing System");
        Table userTable = db.createTable("User");
        User newUser = new User("Username", "First", "Last", "email@gmail.com", "password");
        UUID rowId = newUser.getId();
        
        userTable.insertEntry(ROWTYPE.USER, rowId, newUser);

        User retrievedUser = (User) userTable.readEntry(rowId).getContent();

        assertNotNull(retrievedUser);
        assertEquals(0.0, retrievedUser.getMiles());
    }
}
