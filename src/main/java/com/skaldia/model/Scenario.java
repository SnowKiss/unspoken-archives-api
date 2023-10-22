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
    @Column(columnDefinition = "TEXT")
    private String summary;

    @OneToMany(mappedBy = "scenario")
    private Set<Npc> npcs;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmbiance() {
        return ambiance;
    }

    public void setAmbiance(String ambiance) {
        this.ambiance = ambiance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(Set<Npc> npcs) {
        this.npcs = npcs;
    }
}
