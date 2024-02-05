package com.example.marandproject.api.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is an Entity class represents an entity in the database. It is filled with carriers/airline.
 * Each line in database represents one carrier/airline.
 * Has 2 columns: idCarrier and name. IdCarrier is autoincrement.
 * */
@Entity
@Table(name = "carrier")
public class Carrier {
    @Id
    @SequenceGenerator(
            name = "carrier_sequence",
            sequenceName = "carrier_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "carrier_sequence"
    )
    @Column(name = "id_Carrier")
    Long idCarrier;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Flight> flights = new HashSet<>();

    public Carrier() {}

    public Carrier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
