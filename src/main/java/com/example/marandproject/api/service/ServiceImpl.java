package com.example.marandproject.api.service;

import com.example.marandproject.api.model.Airport;
import com.example.marandproject.api.model.Carrier;
import com.example.marandproject.api.model.Flight;
import com.example.marandproject.api.repository.AirportRepository;
import com.example.marandproject.api.repository.CarrierRepository;
import com.example.marandproject.api.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

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
    public Airport getAirportById(Long airport_id) {
        return airportRepository.findById(airport_id).get();
    }

    @Override
    public Airport getAirportByName(String airport_name) {
        List<Airport> temp =  airportRepository.findAll();
        for(Airport airport : temp){
            if(airport.getName().equals(airport_name))
                return airport;
        }
        return null;
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
    public Carrier getCarrierById(Long carrier_id) {
        return carrierRepository.findById(carrier_id).get();
    }

    @Override
    public Carrier getCarrierByName(String carrier_name) {
        List<Carrier> temp =  carrierRepository.findAll();
        for(Carrier carrier : temp){
            if(carrier.getName().equals(carrier_name))
                return carrier;
        }
        return null;
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

    @Override
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
    ) {
        flightRepository.save(new Flight(
                flightNumber,
                getAirportById(originAirport),
                getAirportById(destinationAirport),
                getCarrierById(carrier),
                price,
                day,
                time,
                duration,
                availableSeats
                ));
    }


    @Override
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
    ) {
        flightRepository.save(new Flight(
                flightNumber,
                getAirportByName(originAirport),
                getAirportByName(destinationAirport),
                getCarrierByName(carrier),
                price,
                day,
                time,
                duration,
                availableSeats
        ));
    }

}
