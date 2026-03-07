package com.fts.flight_ticketing_system.database;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import com.fts.flight_ticketing_system.database.Rows.Row;
import com.fts.flight_ticketing_system.database.Rows.RowFactory;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;

public class Table {
    private String name;
    private HashMap<UUID, Row> rows;
    private ZonedDateTime createdAt;
    private RowFactory rowFactory;

    public Table(String tableName) {
        this.name = tableName;
        this.rows = new HashMap<>();
        this.createdAt = ZonedDateTime.now();

        this.rowFactory = new RowFactory();
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

    public <T> void insertEntry(ROWTYPE type, UUID rowId, T content) throws DataFormatException {
        if (rows.containsKey(rowId)) {
            System.out.println("Duplicate Key (" + rowId + "), cannot Insert Entry!");
        } else {
            Row row = rowFactory.createRow(type, rowId, content);
            rows.put(rowId, row);
        }
        

    }

    public Row readEntry(UUID rowId) {
        return rows.get(rowId);
    }

    public void updateEntry(UUID rowId, HashMap<String, Object> valuesMap) {
        Row<?> row = rows.get(rowId);

        row.updateRow(valuesMap);

    }

    public void deleteEntry(UUID rowId) {
        rows.remove(rowId);
    }
}
