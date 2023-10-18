package com.skaldia.controller;

import com.skaldia.dto.NpcDto;
import com.skaldia.model.Npc;
import com.skaldia.service.NpcService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/npcs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NpcController {

    @Inject
    NpcService npcService;

    @GET
    public List<Npc> getAllNpcs() {
        return npcService.getAllNpcs();
    }

    @GET
    @Path("/{id}")
    public Npc getNpc(@PathParam("id") Long id) {
        return npcService.getNpc(id);
    }

    @POST
    public void createNpc(NpcDto npcDto) {
        npcService.createNpc(npcDto);
    }
}

