package com.example.marandproject.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
public class Flight {
    @Id
    @Column(name = "flight_Number")
    @JsonManagedReference
    String flightNumber;
    @ManyToOne
    @JoinColumn(name = "origin_Airport")
    @JsonManagedReference
    Airport originAirport;
    @ManyToOne
    @JoinColumn(name = "destination_Airport")
    @JsonManagedReference
    Airport destinationAirport;
    @ManyToOne
    @JoinColumn(name = "carrier")
    @JsonManagedReference
    Carrier carrier;
    @Column(name = "price")
    @JsonManagedReference
    double price;
    @Column(name = "day")
    @JsonManagedReference
    String day;
    @Column(name = "time")
    @JsonManagedReference
    String time;
    @Column(name = "duration")
    @JsonManagedReference
    String duration;
    @Column(name = "available_Seats")
    @JsonManagedReference
    int availableSeats;

    public Flight(String flightNumber, Airport originAirport, Airport destinationAirport, Carrier carrier, double price, String day, String time, String duration, int availableSeats) {
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.carrier = carrier;
        this.price = price;
        this.day = day;
        this.time = time;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }

    public Flight() {
    }
}
