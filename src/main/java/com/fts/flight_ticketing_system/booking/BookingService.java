package com.fts.flight_ticketing_system.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.DataFormatException;

import com.fts.flight_ticketing_system.FlightTicketingSystemApplication;
import com.fts.flight_ticketing_system.database.Database;
import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.Row;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;

public class BookingService {
    private Database database = FlightTicketingSystemApplication.database;
    private Table bookingTable;

    public BookingService() {
        bookingTable = database.createTable("bookings");
    }

    public List<HashMap<String, Object>> getAllBookings() {
        Row[] rows = bookingTable.getRows();
        List<HashMap<String, Object>> hashMapRows = new ArrayList<>();
        
        for (Row row : rows) {
            Booking booking = (Booking) row.getContent();

            HashMap<String, Object> jsonHashMap = booking.getBookingAsHashMap();

            hashMapRows.add(jsonHashMap);
        }

        return hashMapRows;
    }

    public void createBooking(Booking booking) throws DataFormatException {
        bookingTable.insertEntry(ROWTYPE.BOOKING, booking.getId(), booking);
    }

    public Booking getBooking(UUID id) {
        return (Booking) bookingTable.readEntry(id).getContent();
    }
    
}
