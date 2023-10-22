package com.skaldia.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "npcs")
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Integer age;
    private String occupation;
    @Column(columnDefinition = "TEXT")
    private String background;
    @Column(columnDefinition = "TEXT")
    private String physicalDescription;
    @Column(columnDefinition = "TEXT")
    private String psychologicalDescription;
    // L'url du portrait est stockée
    @Column(columnDefinition = "TEXT")
    private String portrait;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> adjectives;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "npc_relationships",
            joinColumns = @JoinColumn(name = "npc_id"),
            inverseJoinColumns = @JoinColumn(name = "related_npc_id")
    )
    private Set<Npc> relatedNpcs;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {return birthDate;}

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPhysicalDescription() {
        return physicalDescription;
    }

    public void setPhysicalDescription(String physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    public String getPsychologicalDescription() {
        return psychologicalDescription;
    }

    public void setPsychologicalDescription(String psychologicalDescription) {
        this.psychologicalDescription = psychologicalDescription;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public List<String> getAdjectives() {
        return adjectives;
    }

    public void setAdjectives(List<String> adjectives) {
        this.adjectives = adjectives;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Set<Npc> getRelatedNpcs() {
        return relatedNpcs;
    }

    public void setRelatedNpcs(Set<Npc> relatedNpcs) {
        this.relatedNpcs = relatedNpcs;
    }

    @Override
    public String toString() {
        return "Npc{" +
                "id=" + id +
                ", fileId='" + fileId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", background='" + background + '\'' +
                ", physicalDescription='" + physicalDescription + '\'' +
                ", psychologicalDescription='" + psychologicalDescription + '\'' +
                ", portrait='" + portrait + '\'' +
                ", adjectives=" + adjectives +
                ", scenario=" + scenario +
                ", relatedNpcs=" + relatedNpcs +
                '}';
    }
}