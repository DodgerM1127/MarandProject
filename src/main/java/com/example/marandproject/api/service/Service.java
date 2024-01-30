package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;

import java.util.List;

public interface Service {

    //airport methods

    public String createAirport(Airport airport);
    public String deleteAirport(Long airport_id);
    public Airport getAirport(Long airport_id);
    public List<Airport> getAllAirports();

    //carrier methods

    public String createCarrier(Carrier carrier);
    public String deleteCarrier(Long carrier_id);
    public Carrier getCarrier(Long carrier_id);
    public List<Carrier> getAllCarriers();

    //flight methods

    public String createFlight(Flight flight);
    public Flight getFlight(String flight_Number);
    public List<Flight> getAllFlights();
}
