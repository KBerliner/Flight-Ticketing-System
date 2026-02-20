package com.fts.flight_ticketing_system.database;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class Table {
    private String name;
    private HashMap<String, Row> rows;
    private ZonedDateTime createdAt;

    public Table(String tableName) {
        this.name = tableName;
        this.rows = new HashMap<>();
        this.createdAt = ZonedDateTime.now();
    }
    
    public String getName() {
        return name;
    }

    public HashMap<String, Row> getRows() {
        return rows;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void insertEntry(String rowId, HashMap<String,String> columnsMap) {
        if (rows.containsKey(rowId)) {
            System.out.println("Duplicate Key (" + rowId + "), cannot Insert Entry!");
        } else {
            Row row = new Row(rowId, columnsMap);
            rows.put(rowId, row);
        }
        

    }

    public Row readEntry(String rowId) {
        return rows.get(rowId);
    }

    public void updateEntry(String rowId, HashMap<String,String> valuesMap) {
        Row row = rows.get(rowId);

        if (!row.getRowId().isEmpty()) {
            valuesMap.forEach( (key, value) -> {
                row.getColumnValuesMap().put(key, value);
            });
        }

        row.setUpdatedAt(ZonedDateTime.now());

    }

    public void deleteEntry(String rowId) {
        rows.remove(rowId);
    }
}
