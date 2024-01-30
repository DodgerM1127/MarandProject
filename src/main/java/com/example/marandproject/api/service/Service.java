package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;

import java.util.List;

public interface Service {
    //airport methods
    public void createAirport(Airport airport);
    public void deleteAirport(Long airport_id);
    public Airport getAirport(Long airport_id);
    public List<Airport> getAllAirports();
    //carrier methods
    public void createCarrier(Carrier carrier);
    public void deleteCarrier(Long carrier_id);
    public Carrier getCarrier(Long carrier_id);
    public List<Carrier> getAllCarriers();
    //flight methods
    public void createFlight(Flight flight);
    public Flight getFlight(String flight_Number);
    public List<Flight> getAllFlights();


}
