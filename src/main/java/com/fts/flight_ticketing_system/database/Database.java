package com.fts.flight_ticketing_system.database;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class Database {
    private String name;
    private HashMap<String, Table> tables;
    private ZonedDateTime createdAt;

    public Database(String name) {
        this.name = name;
        this.tables = new HashMap<>();
        this.createdAt = ZonedDateTime.now();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Table> getTables() {
        return tables;
    }

    public Table createTable(String tableName) {
        Table table = new Table(tableName);

        tables.put(tableName, table);

        return tables.get(tableName);
    }

    public void dropTable(String tableName) {
        tables.remove(tableName);
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
