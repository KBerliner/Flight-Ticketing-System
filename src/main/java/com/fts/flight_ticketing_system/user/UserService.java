package com.fts.flight_ticketing_system.user;

import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Row;
import com.fts.flight_ticketing_system.database.Table;

public class UserService {
    private Database database = FlightTicketingSystemApplication.database;
    private Table usersTable;

    public UserService() throws DataFormatException {
        usersTable = database.createTable("users");
    }

    public boolean isNotValidUserInput(HashMap<String, String> user) {
        return 
            user.get("username") == null ||
            user.get("username").isBlank();

    }

    public HashMap<UUID, Row> getAllUsers() {
        return usersTable.getRows();
    }
}
