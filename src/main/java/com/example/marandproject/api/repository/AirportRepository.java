package com.example.marandproject.api.repository;

import com.example.marandproject.api.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
