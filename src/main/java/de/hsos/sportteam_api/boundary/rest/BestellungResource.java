package de.hsos.sportteam_api.boundary.rest;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;

import de.hsos.sportteam_api.entities.DAO.BestellpostMinDAO;
import de.hsos.sportteam_api.gateway.BestellungRepository;
import de.hsos.sportteam_api.gateway.KundenRepository;
import de.hsos.sportteam_api.gateway.PizzaRepository;

@ApplicationScoped
@Path("/bestellungen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellungResource {

    @Inject
    BestellungRepository bestellungRepository;

    @Inject
    KundenRepository kundenRepository;

    @Inject
    PizzaRepository pizzaRepository;
    
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

    // http://localhost:8080/bestellungen/{kunde_id}
    @POST
    @Path("/{kunde_id}")
    @RolesAllowed("KundIn")
    public Response createBestellung(@PathParam("kunde_id") Long kunde_id, BestellpostMinDAO bestellpostDAO) {
        long bestellung_id = bestellungRepository.createBestellung(kunde_id, bestellpostDAO);

        if (bestellung_id > 0)
            return Response.ok(bestellung_id).build();
        return Response.status(Status.NOT_FOUND).build();
    } 
    
    // http://localhost:8080/bestellungen({bestellung_id}/bestellposten)
    @GET
    @Path("/{bestellung_id}/bestellposten")
    @RolesAllowed("KundIn")
    public Response getBestellposten(@PathParam("bestellung_id") Long bestellung_id) {
        return Response.ok(bestellungRepository.getBestellposten(bestellung_id)).build();
    }

    // http://localhost:8080/bestellungen/{bestellung_id}/bestellpost
    @POST
    @Path("/{bestellung_id}/bestellposten")
    @RolesAllowed("KundIn")
    public Response createBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO) {
        if (bestellungRepository.createBestellpost(bestellung_id, bestellpostDAO))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    } 

    @PUT
    @Path("/{bestellung_id}/bestellposten")
    @RolesAllowed("KundIn")
    public Response updateBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO) {
        if (bestellungRepository.updateBestellpost(bestellung_id, bestellpostDAO))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{bestellung_id}/bestellposten")
    @RolesAllowed("KundIn")
    public Response deleteBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        Long pizza_id) {
        if (bestellungRepository.deleteBestellpost(bestellung_id, pizza_id))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }
}
