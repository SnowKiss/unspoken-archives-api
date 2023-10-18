package com.skaldia.service;

import com.skaldia.model.Scenario;
import com.skaldia.repository.ScenarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ScenarioService {

    @Inject
    ScenarioRepository scenarioRepository;

    public List<Scenario> getAllScenarios() {
        return scenarioRepository.listAll();
    }

    public Scenario getScenario(Long id) {
        return scenarioRepository.findById(id);
    }
}
