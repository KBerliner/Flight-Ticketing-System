package com.fts.flight_ticketing_system.user;

import java.util.zip.DataFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/")
    public ResponseEntity<?> hello() throws DataFormatException {
        return ResponseEntity.ok().build();
    }
    
}
