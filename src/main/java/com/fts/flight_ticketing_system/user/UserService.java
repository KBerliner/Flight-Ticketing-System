package com.fts.flight_ticketing_system.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.zip.DataFormatException;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.Row;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;

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

    public List<HashMap<String, Object>> getAllUsers() {
        Row[] rows = usersTable.getRows();
        List<HashMap<String, Object>> hashMapRows = new ArrayList<>();

        for (Row row : rows) {
            User user = (User) row.getContent();

            HashMap<String, Object> jsonHashMap = user.getUserAsHashMap();

            hashMapRows.add(jsonHashMap);
        }

        return hashMapRows;
    }

    public void createUser(User user) throws DataFormatException {
        // TODO: THIS SHOULD NOT JUST THROW THE EXCEPTION, THE EXCEPTION SHOULD BE HANDLED HERE
        usersTable.insertEntry(ROWTYPE.USER, user.getId(), user);
    }

    public User getUser(UUID id) {
        Row row = usersTable.readEntry(id);

        if (row == null) throw new NoSuchElementException();

        User values = (User) row.getContent();
        return values;
    }

    public void updateUser(UUID id, HashMap<String, Object> updates) {
        usersTable.updateEntry(id, updates);
    }

    public void deleteUser(UUID id) {
        usersTable.deleteEntry(id);
    }
}
