package de.hsos.sportteam_api.boundary.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsos.sportteam_api.entities.Pizza;
import de.hsos.sportteam_api.gateway.PizzaRepository;

@ApplicationScoped
@Path("/pizzas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PizzaResource {

    @Inject
    PizzaRepository pizzaRepository;
    
    @PostConstruct
    public void init() {
        pizzaRepository.persistPizza(new Pizza("Margarita", 10L));
    }

    // http://localhost:8080/pizzas
    @GET
    public Response getPizzas() {
        return Response.ok(pizzaRepository.getPizzas()).build();
    }
    
}
