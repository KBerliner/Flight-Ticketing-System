package com.fts.flight_ticketing_system.database.Rows;

import java.util.UUID;
import java.util.zip.DataFormatException;

import com.fts.flight_ticketing_system.booking.Booking;
import com.fts.flight_ticketing_system.flight.Flight;
import com.fts.flight_ticketing_system.user.User;

public class RowFactory {
    public enum ROWTYPE {
        USER,
        FLIGHT,
        BOOKING
    }

    public <T> Row createRow(ROWTYPE type, UUID id, T content) throws DataFormatException {
        switch (type) {
            case USER:
                if (content.getClass() != User.class) throw new DataFormatException();

                return new UserRow(id, (User) content);

            case FLIGHT:
                if (content.getClass() != Flight.class) throw new DataFormatException();

                return new FlightRow(id, (Flight) content);
        
            case BOOKING:
                if (content.getClass() != Booking.class) throw new DataFormatException();

                return new BookingRow(id, (Booking) content);

            default:
                throw new DataFormatException();
        }

    }
}
