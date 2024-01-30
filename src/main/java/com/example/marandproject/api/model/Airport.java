package com.example.marandproject.api.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    public Airport(String name) {
        this.name = name;
    }

    public Long getIdAirport() {
        return idAirport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
