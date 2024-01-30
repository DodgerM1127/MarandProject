package com.example.marandproject;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.repository.AirportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarandProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarandProjectApplication.class, args);
    }
}
