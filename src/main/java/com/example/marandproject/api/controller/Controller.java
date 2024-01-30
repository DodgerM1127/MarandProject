package com.example.marandproject.api.controller;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;
import com.example.marandproject.api.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fbn")
public class Controller {

    Service service;

    public Controller(Service service) {
        this.service = service;
    }

    // airport methods

    @GetMapping("/airport/{airportId}")
    public Airport getAirport(@PathVariable Long airportId){
        return service.getAirport(airportId);
    }

    @GetMapping("/airport")
    public List<Airport> getAllAirports(){
        return service.getAllAirports();
    }

    @PostMapping("/airport")
    public String createAirport(@RequestBody Airport airport){
        service.createAirport(airport);
        return "Airport created";
    }

    @DeleteMapping("/airport/{airportId}")
    public String deleteAirport(@PathVariable Long airportId){
        service.deleteAirport(airportId);
        return "Airport Deleted";
    }

    //carrier methods

    @GetMapping("/carrier/{carrierId}")
    public Carrier getCarrier(@PathVariable Long carrierId){
        return service.getCarrier(carrierId);
    }

    @GetMapping("/carrier")
    public List<Carrier> getAllCarriers(){
        return service.getAllCarriers();
    }

    @PostMapping("/carrier")
    public String createCarrier(@RequestBody Carrier carrier){
        service.createCarrier(carrier);
        return "Carrier created";
    }

    @DeleteMapping("/carrier/{carrierId}")
    public String deleteCarrier(@PathVariable Long carrierId){
        service.deleteCarrier(carrierId);
        return "Carrier Deleted";
    }

    //flight methods

    @GetMapping("/flight/{flight_Number}")
    public Flight getFlight(@PathVariable String flight_Number){
        return service.getFlight(flight_Number);
    }

    @GetMapping("/flight")
    public List<Flight> getAllFlights(){
        return service.getAllFlights();
    }

    @PostMapping("/flight")
    public String createFlight(@RequestBody Flight flight){
        service.createFlight(flight);
        return "Flight created";
    }




}
