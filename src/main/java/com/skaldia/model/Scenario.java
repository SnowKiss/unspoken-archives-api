package com.skaldia.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "scenarios")
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String universe;
    private String period;
    private String location;
    private String ambiance;
    private String summary;

    @OneToMany(mappedBy = "scenario")
    private Set<Npc> npcs;

    // Getters et setters
}
