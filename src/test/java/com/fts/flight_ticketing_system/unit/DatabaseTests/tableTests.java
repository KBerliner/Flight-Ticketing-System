package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Row;
import com.fts.flight_ticketing_system.database.Table;

public class tableTests {
    Table table;
    UUID rowId;
    HashMap<String, Object> columns = new HashMap<>();

    @BeforeEach
    void setUp() {
        // Initialize Table
        String name = "Table Name";
        table = new Table(name);

        // Initialize Row Setup
        rowId = UUID.randomUUID();
        columns.put("Key", "Value");
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
        table.insertEntry(rowId, columns);
 
        assertEquals(columns, table.readEntry(rowId).getColumnValuesMap());
    }

    @Test
    void shouldNotPermitDuplicateRowIds() {
        table.insertEntry(rowId, columns);

        HashMap<String, Object> newColumns = new HashMap<>();
        columns.put("Key_2", "Value_2");

        table.insertEntry(rowId, newColumns);

        HashMap<UUID, Row> resultingRows = table.getRows();

        assertEquals(1, resultingRows.size());
    }

    @Test
    void shouldUpdateRow() {
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

        UUID NON_EXISTENT_ROW_ID = UUID.randomUUID();

        assertThrows(NullPointerException.class, () -> {
            table.updateEntry(NON_EXISTENT_ROW_ID, newColumn);
        });
    }

    @Test
    void shouldDeleteRow() {
        table.insertEntry(rowId, columns);

        table.deleteEntry(rowId);

        assertEquals(0, table.getRows().size());
    }
}
