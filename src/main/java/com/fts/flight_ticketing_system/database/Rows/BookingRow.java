package com.fts.flight_ticketing_system.database.Rows;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

import com.fts.flight_ticketing_system.booking.Booking;

public class BookingRow implements Row {
    private UUID id;
    private Booking booking;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public BookingRow(UUID id, Booking content) {
        this.id = id;
        this.booking = content;
    }

    @Override
    public UUID getRowId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRowId'");
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreatedAt'");
    }

    @Override
    public ZonedDateTime getUpdatedAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUpdatedAt'");
    }

    @Override
    public Booking getContent() {
        return booking;
    }

    @Override
    public void updateRow(HashMap newData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRow'");
    }
    
}
