package com.fts.flight_ticketing_system.user;

import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fts.flight_ticketing_system.database.Row;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController() throws DataFormatException {
        userService = new UserService();
    }

    @GetMapping("/")
    public ResponseEntity<HashMap<UUID, Row>> hello() throws DataFormatException {
        HashMap<UUID, Row> users = userService.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestParam HashMap<String, String> user) {
        if (userService.isNotValidUserInput(user)) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }
    
}
