package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.repository.AirportRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

    AirportRepository airportRepository;
    public ServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
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
}
