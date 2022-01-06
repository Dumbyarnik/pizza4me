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
@Path("/kunden/template/adresse")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class kundeAdresseTemplate {
    @Inject
    KundenRepository kundenRepository;
    @Inject
    KundenAdresseRepository kundenAdresseRepository;

    @Inject 
    Template kundeAdresseTemplate;
    
    @PostConstruct
    public void init() {
    }

    // http://localhost:8080/kunden/template/adresse
    @GET
    @RolesAllowed("KundIn")
    public TemplateInstance getKunde(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();
        Kunde kunde = this.kundenRepository.getKunde(username);

        return this.kundeAdresseTemplate.data("kunde", kunde);
    }

    // http://localhost:8080/kunden/template/adresse
    @POST
    @RolesAllowed("KundIn")
    public TemplateInstance createAdresse(@Context SecurityContext sec, AdresseDAO adresseDAO) {

        Principal user = sec.getUserPrincipal();
        String username = user.getName();
        Kunde kunde = this.kundenRepository.getKunde(username);

        kundenAdresseRepository.createAdresse(username, adresseDAO);
        return this.kundeAdresseTemplate.data("kunde", kunde);
    }

    // http://localhost:8080/kunden/adresse
    @POST
    @Path("/delete")
    @RolesAllowed("KundIn")
    public Response deleteAdresse(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        kundenAdresseRepository.deleteAdresse(username);
        return Response.ok().build();
    }

    // http://localhost:8080/kunden/template/adresse
    @POST
    @Path("/update")
    @RolesAllowed("KundIn")
    public TemplateInstance updateAdresse(@Context SecurityContext sec, AdresseDAO adresseDAO) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();
        Kunde kunde = this.kundenRepository.getKunde(username);

        kundenAdresseRepository.updateAdresse(username, adresseDAO);
        return this.kundeAdresseTemplate.data("kunde", kunde);
    }
}
