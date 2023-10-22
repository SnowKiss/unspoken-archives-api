package com.skaldia.controller;

import com.skaldia.dto.NpcDto;
import com.skaldia.model.Npc;
import com.skaldia.service.NpcService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response createNpc(NpcDto npcDto) {
        Npc createdNpc = npcService.createNpc(npcDto);
        return Response.ok(createdNpc).build();
    }

    @PUT
    @Path("/{id}/regenerate-image")
    public Response regenerateNpcImage(@PathParam("id") Long id) {
        try {
            Npc updatedNpc = npcService.regenerateNpcImage(id);
            System.out.println(updatedNpc.toString());
            return Response.ok(updatedNpc).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

