package com.fts.flight_ticketing_system.flight;

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

public class FlightService {
    private Database database = FlightTicketingSystemApplication.database;
    private Table flightTable;

    public FlightService() {
        flightTable = database.createTable("flights");
    }

    public List<HashMap<String, Object>> getAllFlights() {
        Row[] rows = flightTable.getRows();
        List<HashMap<String, Object>> hashMapRows = new ArrayList<>();
        
        for (Row row : rows) {
            Flight flight = (Flight) row.getContent();

            HashMap<String, Object> jsonHashMap = flight.getFlightAsHashMap();

            hashMapRows.add(jsonHashMap);
        }

        return hashMapRows;
    }

    public void createFlight(Flight flight) throws DataFormatException {
        // TODO: THIS SHOULD NOT JUST THROW THE EXCEPTION, THE EXCEPTION SHOULD BE HANDLED HERE
        flightTable.insertEntry(ROWTYPE.FLIGHT, flight.getId(), flight);
    }

    public Flight getFlight(UUID id) {
        Row row = flightTable.readEntry(id);

        if (row == null) throw new NoSuchElementException();

        Flight values = (Flight) row.getContent();

        return values;
    }
}
