package com.fts.flight_ticketing_system.booking;

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

public class BookingService {
    private Database database = FlightTicketingSystemApplication.database;
    private HashMap<String, Table> tables = database.getTables();
    private Table bookingsTable;

    public BookingService() {
        bookingsTable = database.createTable("bookings");
    }

    public List<HashMap<String, Object>> getAllBookings() {
        Row[] rows = bookingsTable.getRows();
        List<HashMap<String, Object>> hashMapRows = new ArrayList<>();
        
        for (Row row : rows) {
            Booking booking = (Booking) row.getContent();

            HashMap<String, Object> jsonHashMap = booking.getBookingAsHashMap();

            hashMapRows.add(jsonHashMap);
        }

        return hashMapRows;
    }

    public void createBooking(Booking booking) throws DataFormatException {
        bookingsTable.insertEntry(ROWTYPE.BOOKING, booking.getId(), booking);
    }

    public Booking getBooking(UUID id) {
        return (Booking) bookingsTable.readEntry(id).getContent();
    }

    public void deleteBooking(UUID id) {
        if (bookingsTable.readEntry(id) == null) throw new NoSuchElementException();
        bookingsTable.deleteEntry(id);
    }

    public HashMap<String, Object> getUserAndFlight(UUID userId, UUID flightId) {
        System.out.println(userId + " " + flightId);

        HashMap<String, Object> data = new HashMap<>();

        data.put("user", tables.get("users").readEntry(userId).getContent());
        data.put("flight", tables.get("flights").readEntry(flightId).getContent());

        return data;
    }
    
}
