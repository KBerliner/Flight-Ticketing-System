package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Row;
import com.fts.flight_ticketing_system.database.Table;

public class tableTests {
    Table table;

    @BeforeEach
    void initializeTable() {
        String name = "Table Name";

        table = new Table(name);
    }

    @Test
    void shouldCorrectlyInitialize() {

        assertNotNull(table);

        assertEquals("Table Name", table.getName());
        assertNotNull(table.getRows());
        assertNotNull(table.getCreatedAt());
    }

    @Test
    void shouldInsertEntry() {
        String rowId = "ROWID";
        HashMap<String, Object> columns = new HashMap<>();
        columns.put("Key", "Value");

        table.insertEntry(rowId, columns);

        assertEquals(columns, table.readEntry(rowId).getColumnValuesMap());
    }

    @Test
    void shouldNotPermitDuplicateRowIds() {
        String rowId = "ROWID";
        HashMap<String, Object> columns = new HashMap<>();
        columns.put("Key", "Value");

        table.insertEntry(rowId, columns);

        String newRowId = "ROWID";
        HashMap<String, Object> newColumns = new HashMap<>();
        columns.put("Key_2", "Value_2");

        table.insertEntry(newRowId, newColumns);

        HashMap<String, Row> resultingRows = table.getRows();

        assertEquals(1, resultingRows.size());
    }

    @Test
    void shouldUpdateRow() {
        String rowId = "ROWID";
        HashMap<String, Object> columns = new HashMap<>();
        columns.put("Key", "Value");

        table.insertEntry(rowId, columns);

        ZonedDateTime oldUpdatedAt = table.readEntry(rowId).getUpdatedAt();

        HashMap<String, String> newColumn = new HashMap<>();
        newColumn.put("Key", "New Value");

        table.updateEntry(rowId, newColumn);

        ZonedDateTime newUpdatedAt = table.readEntry(rowId).getUpdatedAt();

        assertEquals("New Value", table.readEntry(rowId).getColumnValuesMap().get("Key"));

        assertNotEquals(oldUpdatedAt, newUpdatedAt);
    }

    @Test
    void shouldNotUpdateRow_IfRowDoesNotExist() {
        HashMap<String, String> newColumn = new HashMap<>();
        newColumn.put("Key", "New Value");

        assertThrows(NullPointerException.class, () -> {
            table.updateEntry("NON_EXISTENT", newColumn);
        });
    }

    @Test
    void shouldDeleteRow() {
        String rowId = "ROWID";
        HashMap<String, Object> columns = new HashMap<>();
        columns.put("Key", "Value");

        table.insertEntry(rowId, columns);

        table.deleteEntry(rowId);

        assertEquals(0, table.getRows().size());
    }
}
