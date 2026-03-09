package com.fts.flight_ticketing_system.user;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonGetter;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController() throws DataFormatException {
        userService = new UserService();
    }

    @GetMapping("/")
    public ResponseEntity<List<HashMap<String, Object>>> getAllUsers() throws DataFormatException {
        List<HashMap<String,Object>> users = userService.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @JsonGetter
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getOneUser(@PathVariable("id") UUID id) {
        User user = userService.getUser(id);

        return ResponseEntity.ok(user.getUserAsHashMap());
    }

    @PostMapping(value="/")
    public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody HashMap<String, Object> user) throws DataFormatException {
        if (userService.isNotValidUserInput(user)) return ResponseEntity.badRequest().build();

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

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") UUID id, @RequestBody HashMap<String, Object> updates) {
        User user = userService.getUser(id);
        user.update(updates);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
    
}
