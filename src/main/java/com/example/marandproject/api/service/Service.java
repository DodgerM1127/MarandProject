package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;

import java.util.List;
import java.util.Optional;

public interface Service {

    //airport methods

    public String createAirport(Airport airport);
    public String deleteAirport(Long airport_id);
    public Airport getAirportById(Long airport_id);
    public Airport getAirportByName(String airport_name);
    public List<Airport> getAllAirports();

    //carrier methods
    public String createCarrier(Carrier carrier);
    public String deleteCarrier(Long carrier_id);
    public Carrier getCarrierById(Long carrier_id);
    public Carrier getCarrierByName(String carrier_name);
    public List<Carrier> getAllCarriers();

    //flight methods
    public Flight getFlight(String flight_Number);
    public List<Flight> getAllFlights();
    public String decreaseSeats(String flightName);
    public void createFlightById(
            String flightNumber,
            Long originAirport,
            Long destinationAirport,
            Long carrier,
            double price,
            String day,
            String time,
            String duration,
            int availableSeats
    );
    public void createFlightByName(
            String flightNumber,
            String originAirport,
            String destinationAirport,
            String carrier,
            double price,
            String day,
            String time,
            String duration,
            int availableSeats
    );
}
