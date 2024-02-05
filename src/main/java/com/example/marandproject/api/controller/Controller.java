package com.example.marandproject.api.controller;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;
import com.example.marandproject.api.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/fbn")
public class Controller {

    Service service;

    public Controller(Service service) {
        this.service = service;
    }

    //airport methods

    @GetMapping("/airport/{airportId}")
    public Airport getAirport(@PathVariable Long airportId){
        return service.getAirportById(airportId);
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
    @PostMapping("/airports")
    public String createAirports(@RequestBody ArrayList<Airport> airports){
        for (Airport airport : airports) {
            service.createAirport(airport);
        }
        return "Airports created";
    }

    @DeleteMapping("/airport/{airportId}")
    public String deleteAirport(@PathVariable Long airportId){
        service.deleteAirport(airportId);
        return "Airport Deleted";
    }

    //carrier methods

    @GetMapping("/carrier/{carrierId}")
    public Carrier getCarrier(@PathVariable Long carrierId){
        return service.getCarrierById(carrierId);
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

    @PostMapping("/carriers")
    public String createCarriers(@RequestBody ArrayList<Carrier> carriers){
        for (Carrier carrier : carriers) {
            service.createCarrier(carrier);
        }
        return "Carriers created";
    }

    @DeleteMapping("/carrier/{carrierId}")
    public String deleteCarrier(@PathVariable Long carrierId){
        service.deleteCarrier(carrierId);
        return "Carrier Deleted";
    }

    //flight methods

    @PutMapping("/flight/seats/{flightNumber}")
    public String decreaseFlightSeats(@PathVariable String flightNumber){
        return service.decreaseSeats(flightNumber);
    }

    @GetMapping("/flight/{flightNumber}")
    public Flight getFlight(@PathVariable String flightNumber){
        return service.getFlight(flightNumber);
    }

    @PostMapping("/flight/")
    public String createFlightById(@RequestBody HashMap json){

        service.createFlightById(
                (String) json.get("flightNumber"),
                Long.valueOf((int)json.get("originAirport")),
                Long.valueOf((int)json.get("destinationAirport")),
                Long.valueOf((int) json.get("carrier")),
                (double)json.get("price"),
                (String)json.get("day"),
                (String)json.get("time"),
                (String)json.get("duration"),
                (int)json.get("availableSeats")
        );
        return "flight created";
    }

    @GetMapping("/flight")
    public List<Flight> getAllFlights(){
        return service.getAllFlights();
    }

    @PostMapping("/flights")
    public String createFlightsById(@RequestBody ArrayList<HashMap> jsonArray){

        for(HashMap json : jsonArray) {
            service.createFlightById(
                    (String) json.get("flightNumber"),
                    Long.valueOf((int) json.get("originAirport")),
                    Long.valueOf((int) json.get("destinationAirport")),
                    Long.valueOf((int) json.get("carrier")),
                    (double) json.get("price"),
                    (String) json.get("day"),
                    (String) json.get("time"),
                    (String) json.get("duration"),
                    (int) json.get("availableSeats")
            );
        }
        return "flight created";
    }

    @PostMapping("/flightByName")
    public String createFlightByName(@RequestBody HashMap json){

        service.createFlightByName(
                (String) json.get("flightNumber"),
                (String) json.get("originAirport"),
                (String) json.get("destinationAirport"),
                (String) json.get("carrier"),
                Double.valueOf((Integer)json.get("price")),
                (String)json.get("day"),
                (String)json.get("time"),
                (String)json.get("duration"),
                (int)json.get("availableSeats")
        );
        return "flight created";
    }

    @PostMapping("/flightsByName")
    public String createFlightsByName(@RequestBody ArrayList<HashMap> jsonArray){

        for(HashMap json : jsonArray) {
            service.createFlightByName(
                    (String) json.get("flightNumber"),
                    (String) json.get("originAirport"),
                    (String) json.get("destinationAirport"),
                    (String) json.get("carrier"),
                    Double.valueOf((Integer)json.get("price")),
                    (String) json.get("day"),
                    (String) json.get("time"),
                    (String) json.get("duration"),
                    (int) json.get("availableSeats")
            );
        }
        return "flight created";
    }
}
