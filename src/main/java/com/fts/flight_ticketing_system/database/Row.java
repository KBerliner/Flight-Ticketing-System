package com.fts.flight_ticketing_system.database;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Row {
    private UUID rowId;
    private HashMap<String, Object> columnValuesMap;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Row(UUID rowId, HashMap<String, Object> columnValuesMap) {
        this.rowId = rowId;
        this.columnValuesMap = columnValuesMap;

        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public UUID getRowId() {
        return rowId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public HashMap<String, Object> getColumnValuesMap() {
        return columnValuesMap;
    }

    public void setUpdatedAt(ZonedDateTime newUpdatedAt) {
        this.updatedAt = newUpdatedAt;
    }
}
