package com.fts.flight_ticketing_system.user;

import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        System.out.print("CHECKING GET");
        HashMap<UUID, Row> users = userService.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @PostMapping(value="/")
    public ResponseEntity<?> createUser(@RequestBody HashMap<String, Object> user) throws DataFormatException {
        System.out.print(user + " " + userService.isNotValidUserInput(user));
        if (userService.isNotValidUserInput(user)) return ResponseEntity.badRequest().body("WRONG");

        User constructedUser = new User(
            (String) user.get("username"), 
            (String) user.get("firstName"), 
            (String) user.get("lastName"), 
            (String) user.get("email"), 
            (String) user.get("password")
        );

        userService.createUser(constructedUser);

        return ResponseEntity.status(201).body(constructedUser.getUserAsHashMap());
    }
    
}
