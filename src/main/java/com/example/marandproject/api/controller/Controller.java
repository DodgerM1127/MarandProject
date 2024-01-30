package com.example.marandproject.api.controller;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.repository.AirportRepository;
import com.example.marandproject.api.repository.FlightRepository;
import com.example.marandproject.api.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class Controller {

    Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/{airportId}")
    public Airport getAirport(@PathVariable Long airportId){
        return service.getAirport(airportId);
    }

    @GetMapping()
    public List<Airport> getAllAirports(){
        return service.getAllAirports();
    }

    @PostMapping()
    public String createAirport(@RequestBody Airport airport){
        service.createAirport(airport);
        return "Airport created";
    }

    @DeleteMapping("/{airportId}")
    public String deleteAirport(@PathVariable Long airportId){
        service.deleteAirport(airportId);
        return "Airport Deleted";
    }



}
