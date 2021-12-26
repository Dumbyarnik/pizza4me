package de.hsos.sportteam_api.boundary.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.entities.Pizza;
import de.hsos.sportteam_api.entities.DAO.BestellpostCreateDAO;
import de.hsos.sportteam_api.gateway.BestellungRepository;
import de.hsos.sportteam_api.gateway.KundenRepository;
import de.hsos.sportteam_api.gateway.PizzaRepository;
import io.quarkus.qute.Results.NotFound;

@ApplicationScoped
@Path("/bestellungen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BestellungResource {

    @Inject
    BestellungRepository bestellungRepository;

    @Inject
    KundenRepository kundenRepository;

    @Inject
    PizzaRepository pizzaRepository;
    
    @PostConstruct
    public void init() {
        // Creating kunden
        kundenRepository.createKunde("Alph");
        kundenRepository.persistKunde(new Kunde("Martina", 
            new Adresse ("default", "default", "default", "default")));
        kundenRepository.persistKunde(new Kunde("Jack"));

        // ctreating pizzas
        pizzaRepository.persistPizza(new Pizza("Margarita", "Good pizza", 10L));
        pizzaRepository.persistPizza(new Pizza("Pizza con Fungi", "Cool pizza", 12L));
        pizzaRepository.persistPizza(new Pizza("Pizza con Gorgonzola", "Great pizza", 13L));

        // creating bestellungen
        bestellungRepository.createBestellung(1L);

        //creating bestellposten
        //bestellungRepository.createBestellpost(1L);
    }

    // http://localhost:8080/bestellungen
    @GET
    public Response getBestellungen() {
        return Response.ok(bestellungRepository.getBestellungen()).build();
    }

    // http://localhost:8080/bestellungen/{kunde_id}
    @POST
    @Path("/{kunde_id}")
    public Response createBestellung(@PathParam("kunde_id") Long kunde_id) {
        if (bestellungRepository.createBestellung(kunde_id))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    } 
    
    // http://localhost:8080/bestellungen({bestellung_id}/bestellposten)
    @GET
    @Path("/{bestellung_id}/bestellposten")
    public Response getBestellposten(@PathParam("bestellung_id") Long bestellung_id) {
        return Response.ok(bestellungRepository.getBestellposten(bestellung_id)).build();
    }

    // http://localhost:8080/bestellungen/{bestellung_id}/bestellpost
    @POST
    @Path("/{bestellung_id}/bestellposten")
    public Response createBestellpost(@PathParam("bestellung_id") Long bestellung_id, 
        BestellpostCreateDAO bestellpostDAO) {
        if (bestellungRepository.createBestellpost(bestellung_id, bestellpostDAO))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    } 




}
