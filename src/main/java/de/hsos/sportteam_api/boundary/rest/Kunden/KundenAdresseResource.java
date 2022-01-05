package de.hsos.sportteam_api.boundary.rest.kunden;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.entities.DAO.AdresseDAO;
import de.hsos.sportteam_api.gateway.kunden.KundenAdresseRepository;
import de.hsos.sportteam_api.gateway.kunden.KundenRepository;

@ApplicationScoped
@Path("/kunden/adresse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KundenAdresseResource {
    @Inject
    KundenAdresseRepository kundenAdresseRepository;
    
    @PostConstruct
    public void init() {  
    }

    // http://localhost:8080/kunden/adresse
    @GET
    @RolesAllowed("KundIn")
    public Response getAdresse(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        Adresse adresse = kundenAdresseRepository.getAdresse(username);
        if (adresse != null)
            return Response.ok(adresse).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    // http://localhost:8080/kunden/adresse
    @POST
    @RolesAllowed("KundIn")
    public Response createAdresse(@Context SecurityContext sec, AdresseDAO adresseDAO) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        kundenAdresseRepository.createAdresse(username, adresseDAO);
        return Response.ok().build();
    }

    // http://localhost:8080/kunden/adresse
    @PUT
    @RolesAllowed("KundIn")
    public Response updateAdresse(@Context SecurityContext sec, AdresseDAO adresseDAO) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        kundenAdresseRepository.updateAdresse(username, adresseDAO);
        return Response.ok().build();
    }
    
    // http://localhost:8080/kunden/adresse
    @DELETE
    @RolesAllowed("KundIn")
    public Response deleteAdresse(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        kundenAdresseRepository.deleteAdresse(username);
        return Response.ok().build();
    }
}
