package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;

import java.util.List;

public interface Service {
    public String createAirport(Airport airport);
    public String deleteAirport(Long airport_id);
    public Airport getAirport(Long airport_id);
    public List<Airport> getAllAirports();
}
