package de.hsos.sportteam_api.control;

import java.util.Collection;

import javax.inject.Singleton;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.entities.DAO.AdresseDAO;


@ApplicationScoped
@Singleton
public interface KundenServiceInterface {
    boolean createKunde(String name, String password);
    Collection<Kunde> getKunden();
    Kunde getKunde(long kundeNummer);
    boolean deleteKunde(long kundeNummer);
    void createAdresse(long kundeNummer, AdresseDAO adresseDAO);
    void updateAdresse(long kundeNummer, AdresseDAO neueAdresseDAO);
    Adresse getAdresse(long kundeNummer);
    boolean deleteAdresse(long kundeNummer);
}
