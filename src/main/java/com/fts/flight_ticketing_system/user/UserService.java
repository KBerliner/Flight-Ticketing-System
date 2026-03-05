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

    public boolean isNotValidUserInput(HashMap<String, Object> user) {
        String[] KEYS = {"username", "firstName", "lastName", "email", "password"};

        for (String key : KEYS) {
            String field = (String) user.get(key);
            
            if (field == null || field.isBlank()) return true;
        }

        return false;
    }

    public Row[] getAllUsers() {
        return usersTable.getRows();
    }

    public void createUser(User user) {
        usersTable.insertEntry(user.getId(), user.getUserAsHashMap());
    }

    public HashMap<String, Object> getUser(UUID id) {
        Row row = usersTable.readEntry(id);

        // Returning empty HashMap if row doesn't exist
        if (row == null) return new HashMap<>();

        HashMap<String, Object> values = row.getColumnValuesMap();

        values.remove("password");

        return values;
    }

    public void updateUser(UUID id, HashMap<String, Object> updates) {
        usersTable.updateEntry(id, updates);
    }

    public void deleteUser(UUID id) {
        usersTable.deleteEntry(id);
    }
}
