package com.example.marandproject.api.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<Flight> flights = new HashSet<>();

    public Carrier() {
    }
}
