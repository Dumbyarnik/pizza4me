package de.hsos.sportteam_api.templates.kunde;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import de.hsos.sportteam_api.entities.DAO.AdresseDAO;
import de.hsos.sportteam_api.entities.basic.Kunde;
import de.hsos.sportteam_api.entities.basic.Pizza;
import de.hsos.sportteam_api.gateway.PizzaRepository;
import de.hsos.sportteam_api.gateway.kunden.KundenAdresseRepository;
import de.hsos.sportteam_api.gateway.kunden.KundenRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
@Path("/kunden/template")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class KundeResourceTemplate {
    @Inject
    KundenRepository kundenRepository;
    @Inject
    KundenAdresseRepository kundenAdresseRepository;

    @Inject 
    Template kundeTemplate;

    @Inject 
    Template registerTemplate;
    
    @PostConstruct
    public void init() {
    }

    // http://localhost:8080/kunden/template
    @GET
    @RolesAllowed("KundIn")
    public TemplateInstance getKunde(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();
        Kunde kunde = this.kundenRepository.getKunde(username);

        return this.kundeTemplate.data("kunde", kunde);
    }

    // http://localhost:8080/kunden/template/register
    @GET
    @Path("/register")
    public TemplateInstance getRegister(@Context SecurityContext sec) {
        return this.registerTemplate.instance();
    }
    
    // http://localhost:8080/kunden/template/adresse
    @DELETE
    @RolesAllowed("KundIn")
    public Response deleteAdresse(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        kundenAdresseRepository.deleteAdresse(username);
        return Response.ok().build();
    }
}
