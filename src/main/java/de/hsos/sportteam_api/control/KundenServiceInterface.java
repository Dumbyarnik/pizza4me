package de.hsos.sportteam_api.control;

import java.util.Collection;

import javax.inject.Singleton;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Kunde;


@ApplicationScoped
@Singleton
public interface KundenServiceInterface {
    void kundeAnlegen(String name);
    Collection<Kunde> kundenAbfragen();
    Kunde kundeAbfragen(long kundeNummer);
    boolean kundeLoeschen(long kundeNummer);
    void adresseAnlegen(long kundeNummer, Adresse adresse);
    void adresseAendern(long kundeNummer, Adresse neueAdresse);
    Adresse adresseAbfragen(long kundeNummer);
    boolean adresseLoeschen(long kundeNummer);
}
