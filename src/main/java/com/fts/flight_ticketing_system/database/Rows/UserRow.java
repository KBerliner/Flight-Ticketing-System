package com.fts.flight_ticketing_system.database.Rows;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

import com.fts.flight_ticketing_system.user.User;

public class UserRow implements Row<User> {
    private UUID rowId;
    private User user;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public UserRow(UUID id, User user) {
        this.rowId = id;
        this.user = user;

        ZonedDateTime now = ZonedDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }

    @Override
    public UUID getRowId() {
        return rowId;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(ZonedDateTime newUpdatedAt) {
        this.updatedAt = newUpdatedAt;
    }

    @Override
    public User getContent() {
        return this.user.getSafe();
    }

    @Override
    public void updateRow(HashMap<String, Object> newData) {
        user.update(newData);
    }

}
