package de.hsos.sportteam_api.boundary.rest;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.gateway.KundenRepository;

public class KundenResource implements KundenResourceInterface {


    @Inject
    KundenRepository kundenRepository;
    
    @PostConstruct
    public void init() {
        this.kundenRepository.persistKunde(new Kunde("Martina", 
            new Adresse ("default", "default", "default", "default")));
        this.kundenRepository.persistKunde(new Kunde("Jack"));
    }

    @Override
    public Response getKunden() {
        return Response.ok(kundenRepository.getKunden()).build();
    }

    @Override
    public Response getKunde(Long id) {
        if (kundenRepository.getKunde(id) == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(kundenRepository.getAdresse(id)).build();
    }

    @Override
    public Response createKunde(String name) {
        kundenRepository.createKunde(name);
        return Response.ok().build();
    }

    @Override
    public Response deleteKunde(Long id) {
        if (kundenRepository.deleteKunde(id))
            return Response.ok().build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @Override
    public Response getAdresse(Long id) {
        Adresse tmp = kundenRepository.getAdresse(id);
        if (tmp != null)
            return Response.ok(tmp).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @Override
    public Response createAdresse(Long id, Adresse adresse) {
        kundenRepository.createAdresse(id, adresse);
        return Response.ok().build();
    }

    @Override
    public Response updateAdresse(Long id, Adresse adresse) {
        kundenRepository.updateAdresse(id, adresse);
        return Response.ok().build();
    }
    
    @Override
    public Response deleteAdresse(Long id) {
        kundenRepository.deleteAdresse(id);
        return Response.ok().build();
    }
    
}
