package com.fts.flight_ticketing_system.flight;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private FlightService flightService;

    public FlightController() {
        flightService = new FlightService();
    }

    @GetMapping("/")
    public ResponseEntity shouldGetAllFlights() {


        return ResponseEntity.ok().build();
    }
}