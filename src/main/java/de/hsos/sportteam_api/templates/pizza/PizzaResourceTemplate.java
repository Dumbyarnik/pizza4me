package de.hsos.sportteam_api.templates.pizza;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.sportteam_api.entities.basic.Pizza;
import de.hsos.sportteam_api.gateway.PizzaRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@ApplicationScoped
@Path("/pizzas/template")
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class PizzaResourceTemplate {

    @Inject
    PizzaRepository pizzaRepository;

    @Inject 
    Template pizzasTemplate;

    @Inject 
    Template pizzaTemplate;
    
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
    
    // http://localhost:8080/pizzas/template/{id}
    @GET
    @Path("/{id}")
    @PermitAll
    public TemplateInstance getPizza(@PathParam("id") Long pizza_id) {
        Pizza pizza = pizzaRepository.getPizza(pizza_id);
        if (pizza == null){
            throw new NotFoundException();
        }
        return this.pizzaTemplate.data("pizza", pizza);
    }
}
