package de.hsos.sportteam_api.gateway;

import javax.inject.Singleton;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.security.UserLogin;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class CreateInitialSecEntities {
    @Transactional
    public void loadUsers(@Observes StartupEvent event){
        UserLogin.deleteAll();
        UserLogin.add("user", "user", "KundIn");
    }
}
