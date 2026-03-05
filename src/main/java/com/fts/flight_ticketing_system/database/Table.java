package com.fts.flight_ticketing_system.database;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Table {
    private String name;
    private HashMap<UUID, Row> rows;
    private ZonedDateTime createdAt;

    public Table(String tableName) {
        this.name = tableName;
        this.rows = new HashMap<>();
        this.createdAt = ZonedDateTime.now();
    }
    
    public String getName() {
        return name;
    }

    public Row[] getRows() {
        return (Row[]) rows.values().toArray(new Row[rows.size()]);
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void insertEntry(UUID rowId, HashMap<String, Object> columnsMap) {
        if (rows.containsKey(rowId)) {
            System.out.println("Duplicate Key (" + rowId + "), cannot Insert Entry!");
        } else {
            Row row = new Row(rowId, columnsMap);
            rows.put(rowId, row);
        }
        

    }

    public Row readEntry(UUID rowId) {
        return rows.get(rowId);
    }

    public void updateEntry(UUID rowId, HashMap<String, Object> valuesMap) {
        Row row = rows.get(rowId);

        if (row != null) {
            valuesMap.forEach( (key, value) -> {
                row.getColumnValuesMap().put(key, value);
            });
        }

        row.setUpdatedAt(ZonedDateTime.now());

    }

    public void deleteEntry(UUID rowId) {
        rows.remove(rowId);
    }
}
