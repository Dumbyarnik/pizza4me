package de.hsos.sportteam_api.boundary.rest.Kunden;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.ws.rs.core.MediaType;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.entities.DAO.AdresseDAO;
import de.hsos.sportteam_api.gateway.KundenRepository;

@ApplicationScoped
@Path("/kunden/{id}/adresse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KundenAdresseResource {
    @Inject
    KundenRepository kundenRepository;
    
    @PostConstruct
    public void init() {  
    }

    // http://localhost:8080/kunden/{id}/adresse
    @GET
    @RolesAllowed("KundIn")
    public Response getAdresse(@PathParam("id") Long id) {
        Adresse adresse = kundenRepository.getAdresse(id);
        if (adresse != null)
            return Response.ok(adresse).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    // http://localhost:8080/kunden/{id}/adresse
    @POST
    @RolesAllowed("KundIn")
    public Response createAdresse(@PathParam("id") Long id, AdresseDAO adresseDAO) {
        kundenRepository.createAdresse(id, adresseDAO);
        return Response.ok().build();
    }

    // http://localhost:8080/kunden/{id}/adresse
    @PUT
    @RolesAllowed("KundIn")
    public Response updateAdresse(@PathParam("id") Long id, AdresseDAO adresseDAO) {
        kundenRepository.updateAdresse(id, adresseDAO);
        return Response.ok().build();
    }
    
    // http://localhost:8080/kunden/{id}
    @DELETE
    @RolesAllowed("KundIn")
    public Response deleteAdresse(@PathParam("id") Long id) {
        kundenRepository.deleteAdresse(id);
        return Response.ok().build();
    }
}
