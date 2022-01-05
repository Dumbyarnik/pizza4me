package de.hsos.sportteam_api.boundary.rest.pizza;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.sportteam_api.entities.Pizza;
import de.hsos.sportteam_api.gateway.PizzaRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
@Path("/pizzas/template")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PizzaResourceTemplate {

    @Inject
    PizzaRepository pizzaRepository;

    @Inject 
    Template pizzasTemplate;
    
    @PostConstruct
    public void init() {
    }

    // http://localhost:8080/pizzas/template
    @GET
    @PermitAll
    public TemplateInstance getPizzas() {
        Collection<Pizza> pizzas = pizzaRepository.getPizzas();
        return this.pizzasTemplate.data("pizzas", pizzas);
    }   
}
