package de.hsos.sportteam_api.boundary.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.sportteam_api.entities.Pizza;
import de.hsos.sportteam_api.gateway.BestellungRepository;
import de.hsos.sportteam_api.gateway.PizzaRepository;
import io.quarkus.qute.Results.NotFound;

@ApplicationScoped
@Path("/bestellungen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellungResource {

    @Inject
    BestellungRepository bestellungRepository;
    
    @PostConstruct
    public void init() {
    }

    // http://localhost:8080/bestellungen
    @GET
    public Response getBestellungen() {
        return Response.ok(bestellungRepository.getBestellungen()).build();
    }

    // http://localhost:8080/bestellungen/{kunde_id}
    @POST
    @Path("/{kunde_id}")
    public Response createBestellung(@PathParam("kunde_id") Long kunde_id) {
        if (bestellungRepository.createBestellung(kunde_id))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }   
}
