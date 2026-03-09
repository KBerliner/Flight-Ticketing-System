package com.fts.flight_ticketing_system.flight;

import java.util.List;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<HashMap<String, Object>>> getAllFlights() {
        List<HashMap<String, Object>> flights = flightService.getAllFlights();

        return ResponseEntity.ok().body(flights);
    }

    @PostMapping("/")
    public ResponseEntity createFlight(@RequestBody HashMap<String, String> flightDetails) throws DataFormatException {
        System.out.print("\nHERE ARE THE ITEMS YOURE LOOKING FOR: \n" + flightDetails.toString() + "\n");

        Flight constructedFlight = new Flight(
            (Double) Double.valueOf(flightDetails.get("distance")),
            (ZonedDateTime) ZonedDateTime.parse(flightDetails.get("departure")),
            (Duration) Duration.parse(flightDetails.get("duration"))
        );

        flightService.createFlight(constructedFlight);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFlight(@PathVariable("id") UUID id, @RequestBody HashMap<String, Object> updates) {
        flightService.updateFlight(id, updates);

        return ResponseEntity.ok().build();
    }
}