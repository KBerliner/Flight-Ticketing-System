package com.fts.flight_ticketing_system.unit.DatabaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Table;
import com.fts.flight_ticketing_system.database.Rows.Row;
import com.fts.flight_ticketing_system.database.Rows.RowFactory.ROWTYPE;
import com.fts.flight_ticketing_system.user.User;

public class tableTests {
    Table table;
    UUID rowId;
    User content;
    ROWTYPE type;

    @BeforeEach
    void setUp() throws DataFormatException {
        // Initialize Table
        String name = "Table Name";
        table = new Table(name);

        // Initialize Row Setup (Starting with User)
        content = new User("Username", "First", "Last", "email@gmail.com", "Password");
        rowId = content.getId();
        type = ROWTYPE.USER;
    }

    @Test
    void shouldCorrectlyInitialize() {

        assertNotNull(table);

        assertEquals("Table Name", table.getName());
        assertNotNull(table.getRows());
        assertNotNull(table.getCreatedAt());
    }

    @Test
    void shouldInsertEntry() throws DataFormatException {
        table.insertEntry(type, rowId, content);
 
        assertEquals(content, table.readEntry(rowId).getContent());
    }

    @Test
    void shouldNotPermitDuplicateRowIds() throws DataFormatException {
        table.insertEntry(type, rowId, content);

        User newContent = new User(null, null, null, "thisisadifferentemail@gmail.com", "Password");

        table.insertEntry(type, rowId, newContent);

        Row[] resultingRows = table.getRows();

        assertEquals(1, resultingRows.length);
    }

    @Test
    void shouldUpdateRow() throws DataFormatException {
        table.insertEntry(type, rowId, content);

        ZonedDateTime oldUpdatedAt = table.readEntry(rowId).getUpdatedAt();

        HashMap<String, Object> newContent = new HashMap<>();
        newContent.put("username", "New Username");
        content.update(newContent);

        table.updateEntry(rowId, newContent);

        User newRow = (User) table.readEntry(rowId).getContent();

        ZonedDateTime newUpdatedAt = table.readEntry(rowId).getUpdatedAt();

        assertEquals("New Username", newRow.getUsername());
        assertNotEquals(oldUpdatedAt, newUpdatedAt);
    }

    @Test
    void shouldNotUpdateRow_IfRowDoesNotExist() {
        HashMap<String, Object> newColumn = new HashMap<>();
        newColumn.put("Key", "New Value");

        UUID NON_EXISTENT_ROW_ID = UUID.randomUUID();

        assertThrows(NullPointerException.class, () -> {
            table.updateEntry(NON_EXISTENT_ROW_ID, newColumn);
        });
    }

    @Test
    void shouldDeleteRow() throws DataFormatException {
        table.insertEntry(type, rowId, content);

        table.deleteEntry(rowId);

        assertEquals(0, table.getRows().length);
    }
}
