package com.fts.flight_ticketing_system.database.Rows;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

public interface Row<T> {
    public UUID getRowId();
    public ZonedDateTime getCreatedAt();
    public ZonedDateTime getUpdatedAt();
    public void setUpdatedAt(ZonedDateTime newUpdatedAt);

    public T getContent();
    public void updateRow(HashMap<String, Object> newData);
}
