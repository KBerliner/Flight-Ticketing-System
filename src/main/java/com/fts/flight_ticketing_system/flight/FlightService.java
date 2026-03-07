package com.fts.flight_ticketing_system.flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.Row;

public class FlightService {
    private Database database = FlightTicketingSystemApplication.database;
    private Table flightTable;

    public FlightService() {
        flightTable = database.createTable("flights");
    }

    public List getAllFlights() {
        Row[] rows = flightTable.getRows();
        List<HashMap<String, Object>> hashMapRows = new ArrayList<>();

        for (Row row : rows) {
            Flight flight = (Flight) row.getContent();

            HashMap<String, Object> jsonHashMap = flight.getflightAsHashMap();

            hashMapRows.add(jsonHashMap);
        }

        return hashMapRows;
    }
}
