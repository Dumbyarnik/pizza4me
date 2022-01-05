package de.hsos.sportteam_api.boundary.rest.bestellung;

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
import de.hsos.sportteam_api.gateway.PizzaRepository;
import de.hsos.sportteam_api.gateway.bestellung.BestellpostenRepository;
import de.hsos.sportteam_api.gateway.kunden.KundenRepository;

@ApplicationScoped
@Path("/bestellungen/{bestellung_id}/bestellposten")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellpostenResource {
    @Inject
    BestellpostenRepository bestellpostenRepository;
    
    @PostConstruct
    public void init() {
    }

    // http://localhost:8080/bestellungen({bestellung_id}/bestellposten)
    @GET
    @RolesAllowed("KundIn")
    public Response getBestellposten(@PathParam("bestellung_id") Long bestellung_id) {
        return Response.ok(bestellpostenRepository.getBestellposten(bestellung_id)).build();
    }

    // http://localhost:8080/bestellungen/{bestellung_id}/bestellpost
    @POST
    @RolesAllowed("KundIn")
    public Response createBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO) {
        if (bestellpostenRepository.createBestellpost(bestellung_id, bestellpostDAO))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    } 

    @PUT
    @RolesAllowed("KundIn")
    public Response updateBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO) {
        if (bestellpostenRepository.updateBestellpost(bestellung_id, bestellpostDAO))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed("KundIn")
    public Response deleteBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        Long pizza_id) {
        if (bestellpostenRepository.deleteBestellpost(bestellung_id, pizza_id))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }
}
