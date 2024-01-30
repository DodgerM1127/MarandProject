package com.example.marandproject.api.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
public class Flight {
    @Id
    @Column(name = "flightNumber")
    String flightNumber;
    @ManyToOne
    @JoinColumn(name = "originAirport")
    Airport originAirport;
    @ManyToOne
    @JoinColumn(name = "destinationAirport")
    Airport destinationAirport;
    @ManyToOne
    @JoinColumn(name = "carrier")
    Carrier carrier;
    @Column(name = "price")
    double price;
    @Column(name = "day")
    String day;
    @Column(name = "time")
    Time time;
    @Column(name = "duration")
    Time duration;
    @Column(name = "availableSeats")
    int availableSeats;

    public Flight(String flightNumber, Airport originAirport, Airport destinationAirport, Carrier carrier, double price, String day, Time time, Time duration, int availableSeats) {
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
