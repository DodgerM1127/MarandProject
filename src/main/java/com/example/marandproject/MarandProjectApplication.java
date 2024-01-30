package com.example.marandproject;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;
import com.example.marandproject.api.repository.AirportRepository;
import com.example.marandproject.api.repository.CarrierRepository;
import com.example.marandproject.api.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import java.sql.Time;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarandProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarandProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (AirportRepository airportRepository, FlightRepository flightRepository, CarrierRepository carrierRepository){
        return args -> {
            Airport MC1 = new Airport("Ljubljana");
            airportRepository.save(MC1);
            Airport MC2 = new Airport("novomesto");
            airportRepository.save(MC2);
            Airport MC3 = new Airport("kocevje");
            airportRepository.save(MC3);
            Carrier Ca = new Carrier("bob");
            carrierRepository.save(Ca);
            Carrier Ca2 = new Carrier("lolek");
            carrierRepository.save(Ca2);
            Flight num1 = new Flight("SA001",
                    MC1,
                    MC2,
                    Ca,
                    300,
                    "mon",
                    new Time(15,0,0),
                    new Time(15,0,0),
                    5);
            Flight num2 = new Flight("SA002",
                    MC2,
                    MC1,
                    Ca2,
                    300,
                    "mon",
                    new Time(15,0,0),
                    new Time(15,0,0),
                    5);
            Flight num3 = new Flight("SA003",
                    MC3,
                    MC2,
                    Ca2,
                    300,
                    "mon",
                    new Time(15,0,0),
                    new Time(15,0,0),
                    5);
            flightRepository.save(num1);
            flightRepository.save(num2);
            flightRepository.save(num3);
        };
    }
}
