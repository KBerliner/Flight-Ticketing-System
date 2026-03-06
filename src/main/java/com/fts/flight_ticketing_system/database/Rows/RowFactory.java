package com.fts.flight_ticketing_system.database.Rows;

import java.util.UUID;
import java.util.zip.DataFormatException;

import com.fts.flight_ticketing_system.user.User;

public class RowFactory {
    public enum ROWTYPE {
        USER
    }

    public <T> Row createRow(ROWTYPE type, UUID id, T content) throws DataFormatException {
        switch (type) {
            case USER:
                if (content.getClass() != User.class) throw new DataFormatException();

                return new UserRow(id, (User) content);
        
            default:
                break;
        }

        return new UserRow(null, null);
    }
}
