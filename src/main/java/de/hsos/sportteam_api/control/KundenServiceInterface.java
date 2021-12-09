package de.hsos.sportteam_api.control;

import java.util.Collection;

import javax.inject.Singleton;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;


@ApplicationScoped
@Singleton
public interface KundenServiceInterface {
    void createKunde(String name);
    Collection<Kunde> getKunden();
    Kunde getKunde(long kundeNummer);
    boolean deleteKunde(long kundeNummer);
    void createAdresse(long kundeNummer, Adresse adresse);
    void updateAdresse(long kundeNummer, Adresse neueAdresse);
    Adresse getAdresse(long kundeNummer);
    boolean deleteAdresse(long kundeNummer);
}
