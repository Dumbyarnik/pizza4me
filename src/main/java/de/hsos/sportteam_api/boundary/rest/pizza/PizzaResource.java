package de.hsos.sportteam_api.boundary.rest.pizza;

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

import de.hsos.sportteam_api.entities.basic.Pizza;
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
        this.pizzaRepository.persistPizza(new Pizza("Margarita",
            "Classic", 10L));
        this.pizzaRepository.persistPizza(new Pizza("Con Fungi",
            "Good", 12L));
    }

    // http://localhost:8080/pizzas
    @GET
    @PermitAll
    public Response getPizzas() {
        return Response.ok(pizzaRepository.getPizzas()).build();
    }

    // http://localhost:8080/pizzas/{id}
    @GET
    @Path("/{id}")
    @PermitAll
    public Response getPizza(@PathParam("id") Long pizza_id) {
        Pizza pizza = pizzaRepository.getPizza(pizza_id);
        if (pizza == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(pizza).build();
    }
    
    // http://localhost:8080/pizzas/{id}/description
    @GET
    @Path("/{id}/description")
    @PermitAll
    public Response getPizzaDescription(@PathParam("id") Long pizza_id) {
        String description = pizzaRepository.getDescription(pizza_id);
        if (description == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(description).build();
    }

    // http://localhost:8080/pizzas/{id}/price
    @GET
    @Path("/{id}/price")
    @PermitAll
    public Response getPizzaPrice(@PathParam("id") Long pizza_id) {
        Long price = pizzaRepository.getPrice(pizza_id);
        if (price == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(price).build();
    }
    
}
