package de.hsos.sportteam_api.boundary.rest.bestellung;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;

import de.hsos.sportteam_api.entities.DAO.BestellpostMinDAO;
import de.hsos.sportteam_api.gateway.bestellung.BestellungRepository;
import de.hsos.sportteam_api.gateway.PizzaRepository;

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
    @Operation(summary = "Admin Function")
    @PermitAll
    public Response getBestellungen() {
        return Response.ok(bestellungRepository.getBestellungen()).build();
    }

    // http://localhost:8080/bestellungen/
    @POST
    @RolesAllowed("KundIn")
    public Response createBestellung(@Context SecurityContext sec, BestellpostMinDAO bestellpostDAO) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();
        
        long bestellung_id = bestellungRepository
            .createBestellung(username, bestellpostDAO);

        if (bestellung_id > 0)
            return Response.ok(bestellung_id).build();
        return Response.status(Status.NOT_FOUND).build();
    } 
}
