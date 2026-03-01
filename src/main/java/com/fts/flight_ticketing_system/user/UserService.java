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
            user.get("username").isBlank() ||
            user.get("firstName") == null ||
            user.get("firstName").isBlank() ||
            user.get("lastName") == null ||
            user.get("lastName").isBlank();
    }

    public HashMap<UUID, Row> getAllUsers() {
        return usersTable.getRows();
    }

    public void createUser(User user) {
        usersTable.insertEntry(user.getId(), user.getUserAsHashMap());
    }

    public HashMap<String, Object> getUser(UUID id) {
        Row row = usersTable.readEntry(id);

        // Returning empty HashMap if row doesn't exist
        if (row == null) return new HashMap<>();

        return row.getColumnValuesMap();
    }

    public void updateUser(UUID id, HashMap<String, Object> updates) {
        usersTable.updateEntry(id, updates);
    }

    public void deleteUser(UUID id) {
        usersTable.deleteEntry(id);
    }
}
