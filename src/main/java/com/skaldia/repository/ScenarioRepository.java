package com.skaldia.repository;

import com.skaldia.model.Scenario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScenarioRepository implements PanacheRepository<Scenario> {
    // Méthodes personnalisées si nécessaire
}
