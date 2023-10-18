package com.skaldia.repository;

import com.skaldia.model.Npc;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NpcRepository implements PanacheRepository<Npc> {
    // Méthodes personnalisées si nécessaire
}
