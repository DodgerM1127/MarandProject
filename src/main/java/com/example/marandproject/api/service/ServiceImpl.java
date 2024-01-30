package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.repository.AirportRepository;
import com.example.marandproject.api.repository.CarrierRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

    AirportRepository airportRepository;
    CarrierRepository carrierRepository;
    public ServiceImpl(AirportRepository airportRepository, CarrierRepository carrierRepository) {
        this.airportRepository = airportRepository;
        this.carrierRepository = carrierRepository;
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
}
