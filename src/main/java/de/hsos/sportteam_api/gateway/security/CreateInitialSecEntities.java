package de.hsos.sportteam_api.gateway.security;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.security.UserLogin;
import de.hsos.sportteam_api.gateway.kunden.KundenRepository;
import io.quarkus.runtime.StartupEvent;



@Singleton
public class CreateInitialSecEntities {
    @Inject
    KundenRepository kundenRepository;

    @Transactional
    public void loadUsers(@Observes StartupEvent event){
        UserLogin.deleteAll();
        kundenRepository.createKunde("user", "user");
    }
}
