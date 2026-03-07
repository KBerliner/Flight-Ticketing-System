package com.fts.flight_ticketing_system.flight;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Flight {
    public enum STATUS {
        UPCOMING,
        EN_ROUTE,
        LANDED
    }

    private UUID id;
    private Double distance;
    private ZonedDateTime departure;
    private Duration duration;
    private STATUS status;

    public Flight(Double distance, ZonedDateTime departure, Duration duration) {
        this.distance = distance;
        this.departure = departure;
        this.duration = duration;

        this.status = STATUS.UPCOMING;

        this.id = UUID.randomUUID();
    }

    public Double getDistance() {
        return distance;
    }

    public STATUS getStatus() {
        return status;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public Duration getDuration() {
        ZonedDateTime now = ZonedDateTime.now();
        Duration passedFlightTime = Duration.between(departure, now);
        Duration lateDuration = Duration.ofHours(passedFlightTime.compareTo(duration));

        if (lateDuration.isPositive()) {

            return passedFlightTime;
        }
        return duration;
    }

    public ZonedDateTime getArrival() {
        return departure.plus(getDuration());
    }

    public void takeOff() {
        setStatus(Flight.STATUS.EN_ROUTE);

        setDeparture(now());
    }

    private void setDeparture(ZonedDateTime newDeparture) {
        this.departure = newDeparture;
    }

    private void setStatus(STATUS status) {
        this.status = status;
    }

    private ZonedDateTime now() {
        return ZonedDateTime.now();
    }

    public void land() {
        setStatus(Flight.STATUS.LANDED);

        Duration duration = Duration.ofHours(departure.compareTo(now()));
        setDuration(duration);
    }

    private void setDuration(Duration duration) {
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public HashMap<String, Object> getFlightAsHashMap() {
        HashMap<String, Object> flightHashMap = new HashMap<>();

        flightHashMap.put("id", id);
        flightHashMap.put("distance", distance);
        flightHashMap.put("departure", departure);
        flightHashMap.put("duration", duration);
        flightHashMap.put("status", status);

        return flightHashMap;
    }

    public void update(HashMap<String, Object> newDetails) {
        Set<String> keys = newDetails.keySet();

        keys.forEach(key -> {
            switch (key) {
                case "status":
                    if (newDetails.get(key).getClass() == STATUS.class) {
                        STATUS updatedStatus = (STATUS) newDetails.get(key);
                        setStatus(updatedStatus);
                    }
                    break;
                
                case "distance":
                    setDistance((Double) newDetails.get(key));
                    break;
            
                case "departure":
                    setDeparture((ZonedDateTime) newDetails.get(key));
                    break;

                case "duration":
                    setDuration((Duration) newDetails.get(key));
                    break;

                default:
                    break;
            }
        });
    }

    private void setDistance(Double newDistance) {
        this.distance = newDistance;
    }

}
