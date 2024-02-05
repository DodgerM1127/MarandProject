package com.example.marandproject.api.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is an Entity class represents an entity in the database. It is filled with airports.
 * Each line in database represents one airport.
 * Has 2 columns: idAirport and name. IdAirport is autoincrement.
 * */
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "airport_sequence"
    )
    @Column(name = "id_Airport")
    Long idAirport;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Flight> flightsTo = new HashSet<>();

    @OneToMany(mappedBy = "originAirport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Flight> flightsFrom = new HashSet<>();

    public Airport() {
    }
    public String getName() {
        return name;
    }
}
