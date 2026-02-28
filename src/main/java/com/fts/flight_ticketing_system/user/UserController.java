package com.fts.flight_ticketing_system.user;

import java.util.zip.DataFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // private Database database = FlightTicketingSystemApplication.database;
    // private Table usersTable;

    // public UserController() {
    //     usersTable = database.createTable("users");
    // }

    @GetMapping("/")
    public ResponseEntity<?> hello() throws DataFormatException {
        return ResponseEntity.ok().build();
    }
    
}
