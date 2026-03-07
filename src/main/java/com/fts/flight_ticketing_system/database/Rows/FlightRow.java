package com.fts.flight_ticketing_system.database.Rows;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

import com.fts.flight_ticketing_system.flight.Flight;

public class FlightRow implements Row<Flight> {
    private UUID id;
    private Flight flight;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public FlightRow(UUID id, Flight flight) {
        this.id = id;
        this.flight = flight;
    
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    @Override
    public UUID getRowId() {
        return id;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    private void setUpdatedAt(ZonedDateTime newUpdatedAt) {
        this.updatedAt = newUpdatedAt;
    }

    @Override
    public Flight getContent() {
        return flight;
    }

    @Override
    public void updateRow(HashMap<String, Object> newData) {
        flight.update(newData);
        setUpdatedAt(ZonedDateTime.now());
    }
    
}
