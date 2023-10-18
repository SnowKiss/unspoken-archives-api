package com.skaldia.controller;

import com.skaldia.model.Scenario;
import com.skaldia.service.ScenarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/scenarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScenarioController {

    @Inject
    ScenarioService scenarioService;

    @GET
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GET
    @Path("/{id}")
    public Scenario getScenario(@PathParam("id") Long id) {
        return scenarioService.getScenario(id);
    }
}

