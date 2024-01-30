package com.example.marandproject.api.repository;

import com.example.marandproject.api.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository  extends JpaRepository<Flight, String> {

}
