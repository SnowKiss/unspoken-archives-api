package com.skaldia.service;

import com.skaldia.dto.NpcDto;
import com.skaldia.model.Npc;
import com.skaldia.repository.NpcRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class NpcService {

    @Inject
    NpcRepository npcRepository;

    public List<Npc> getAllNpcs() {
        return npcRepository.listAll();
    }

    public Npc getNpc(Long id) {
        return npcRepository.findById(id);
    }

    @Transactional
    public void createNpc(NpcDto npcDto) {
        Npc npc = new Npc();
        npc.setFirstName(npcDto.getFirstName());
        npc.setLastName(npcDto.getLastName());
        npc.setAge(npcDto.getAge());
        npc.setOccupation(npcDto.getOccupation());
        // TODO set les autres champs via des appel d'API GenAI
        npcRepository.persist(npc);
    }
}

