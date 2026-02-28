package com.fts.flight_ticketing_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.fts.flight_ticketing_system.database.Database;

@SpringBootApplication
public class FlightTicketingSystemApplication {

	// public static Database database = new Database("Core");


	public static void main(String[] args) {
		SpringApplication.run(FlightTicketingSystemApplication.class, args);
	}

}
