package com.fts.flight_ticketing_system.database;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class Row {
    private String rowId;
    private HashMap<String, String> columnValuesMap;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Row(String rowId, HashMap<String, String> columnValuesMap) {
        this.rowId = rowId;
        this.columnValuesMap = columnValuesMap;

        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public String getRowId() {
        return rowId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public HashMap<String, String> getColumnValuesMap() {
        return columnValuesMap;
    }

    public void setUpdatedAt(ZonedDateTime newUpdatedAt) {
        this.updatedAt = newUpdatedAt;
    }
}
