package com.example.marandproject.api.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carrier")
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarrier")
    Long idCarrier;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Flight> flights = new HashSet<>();

    public Carrier() {}

    public Carrier(String name) {
        this.name = name;
    }

    public Long getIdCarrier() {
        return idCarrier;
    }

    public String getName() {
        return name;
    }
}