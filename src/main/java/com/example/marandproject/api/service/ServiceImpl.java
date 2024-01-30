package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;
import com.example.marandproject.api.repository.AirportRepository;
import com.example.marandproject.api.repository.CarrierRepository;
import com.example.marandproject.api.repository.FlightRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

    AirportRepository airportRepository;
    CarrierRepository carrierRepository;
    FlightRepository flightRepository;
    public ServiceImpl(AirportRepository airportRepository, CarrierRepository carrierRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.carrierRepository = carrierRepository;
        this.flightRepository = flightRepository;
    }


    @Override
    public String createAirport(Airport airport) {
        airportRepository.save(airport);
        return "airport saved";
    }

    @Override
    public String deleteAirport(Long airport_id) {
        airportRepository.deleteById(airport_id);
        return "airport deleted";
    }

    @Override
    public Airport getAirport(Long airport_id) {
        return airportRepository.findById(airport_id).get();
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public String createCarrier(Carrier carrier) {
        carrierRepository.save(carrier);
        return "carrier saved";
    }

    @Override
    public String deleteCarrier(Long carrier_id) {
        carrierRepository.deleteById(carrier_id);
        return "carrier deleted";
    }

    @Override
    public Carrier getCarrier(Long carrier_id) {
        return carrierRepository.findById(carrier_id).get();
    }

    @Override
    public List<Carrier> getAllCarriers() {
        return carrierRepository.findAll();
    }

    @Override
    public String createFlight(Flight flight) {
        flightRepository.save(flight);
        return "flight created";
    }

    @Override
    public Flight getFlight(String flight_Number) {
        return flightRepository.findById(flight_Number).get();
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
