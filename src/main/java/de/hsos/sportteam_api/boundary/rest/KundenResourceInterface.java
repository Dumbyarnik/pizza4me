package de.hsos.sportteam_api.boundary.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsos.sportteam_api.entities.Adresse;

import javax.ws.rs.PathParam;

@ApplicationScoped
@Path("/kunden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface KundenResourceInterface {
    
    // http://localhost:8080/kunden
    @GET
    public Response getKunden(); 

    // http://localhost:8080/kunden/{id}
    @GET
    @Path("/{id}")
    public Response getKunde(@PathParam("id") Long id); 

    // http://localhost:8080/kunden
    @POST
    public Response createKunde(@QueryParam("name") String name); 

    // http://localhost:8080/kunden/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteKunde(@PathParam("id") Long id);

    // http://localhost:8080/kunden/{id}/adresse
    @GET
    @Path("/{id}/adresse")
    public Response getAdresse(@PathParam("id") Long id); 

    // http://localhost:8080/kunden/{id}/adresse
    @POST
    @Path("/{id}/adresse")
    public Response createAdresse(@PathParam("id") Long id, Adresse adresse); 

    // http://localhost:8080/kunden/{id}/adresse
    @PUT
    @Path("/{id}/adresse")
    public Response updateAdresse(@PathParam("id") Long id, Adresse adresse);  

    // http://localhost:8080/kunden/{id}
    @DELETE
    @Path("/{id}/adresse")
    public Response deleteAdresse(@PathParam("id") Long id);


    
}
