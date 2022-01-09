package de.hsos.sportteam_api.templates.bestellung;

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

import de.hsos.sportteam_api.entities.DAO.BestellpostMinDAO;
import de.hsos.sportteam_api.entities.basic.Bestellung;
import de.hsos.sportteam_api.entities.basic.Pizza;
import de.hsos.sportteam_api.gateway.PizzaRepository;
import de.hsos.sportteam_api.gateway.bestellung.BestellpostenRepository;
import de.hsos.sportteam_api.gateway.bestellung.BestellungRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
@Path("/bestellung/template")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class bestellungTemplate {
    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    BestellungRepository bestellungRepository;

    @Inject
    BestellpostenRepository bestellpostenRepository;

    @Inject 
    Template bestellungTemplate;

    @PostConstruct
    public void init() {
    }

    // http://localhost:8080/bestellung/template
    @GET
    @RolesAllowed("KundIn")
    public TemplateInstance getBestellungTemplate(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();

        Bestellung last_bestellung = bestellungRepository.getLastBestellung(username);
        Collection<Pizza> pizzas = pizzaRepository.getPizzas();

        return this.bestellungTemplate.data("pizzas", pizzas)
            .data("last_bestellung", last_bestellung);
    }

    // http://localhost:8080/bestellung/template
    @POST
    @RolesAllowed("KundIn")
    public Response createBestellung(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String username = user.getName();
        
        long bestellung_id = bestellungRepository
            .createBestellung(username);

        if (bestellung_id > 0)
            return Response.ok(Long.toString(bestellung_id)).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Path("/{bestellung_id}/update")
    @RolesAllowed("KundIn")
    public Response updateBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO) {
        if (bestellpostenRepository.updateBestellpost(bestellung_id, bestellpostDAO))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Path("/{bestellung_id}/delete/{pizza_id}")
    @RolesAllowed("KundIn")
    public Response deleteBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        @PathParam("pizza_id") Long pizza_id) {
        if (bestellpostenRepository.deleteBestellpost(bestellung_id, pizza_id))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }
    
}
