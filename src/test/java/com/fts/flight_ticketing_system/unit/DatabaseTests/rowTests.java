package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Row;

public class rowTests {
    @Test
    void shouldCorrectlyInitialize() {
        String rowId = "abcd1234";

        HashMap<String, String> exampleRowColumns = new HashMap<>();
        exampleRowColumns.put("Key", "Value");

        Row row = new Row(rowId, exampleRowColumns);

        assertEquals(rowId, row.getRowId());
        assertEquals(exampleRowColumns, row.getColumnValuesMap());

        assertNotNull(row.getCreatedAt());
        assertNotNull(row.getUpdatedAt());
    }
}
