package de.hsos.sportteam_api.gateway;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.control.KundenServiceInterface;
import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Bestellpost;
import de.hsos.sportteam_api.entities.Bestellung;
import de.hsos.sportteam_api.entities.Kunde;

@Model
@Dependent
public class KundenRepository implements KundenServiceInterface, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    @Transactional
    public void persistKunde (Kunde kunde){
        em.persist(kunde);
    }

    @Transactional
    @Override
    public void createKunde(String name) {
        Kunde tmp = new Kunde(name);
        em.persist(tmp);
    }

    @Override
    public Collection<Kunde> getKunden() {
        Collection <Kunde> kunden = em.createQuery("SELECT k FROM Kunde k",
            Kunde.class).getResultList();
        // deleting looping elements
        for (Kunde kunde : kunden){
            for (Bestellung bestellung : kunde.getBestellungen()){
                bestellung.deleteKunde();
                bestellung.deleteBestellposten();
            }
        }
        return kunden;
    }

    @Override
    public Kunde getKunde(long kundeNummer) {
        Kunde kunde = em.find(Kunde.class, kundeNummer);
        for (Bestellung bestellung : kunde.getBestellungen()){
            bestellung.deleteKunde();
            bestellung.deleteBestellposten();
        }
        return kunde;
    }

    @Transactional
    @Override
    public boolean deleteKunde(long kundeNummer) {
        Kunde tmp = em.find(Kunde.class, kundeNummer);
        if (tmp != null){
            em.remove(tmp);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void createAdresse(long kundeNummer, Adresse adresse) {
        Kunde tmp = em.find(Kunde.class, kundeNummer);
        if (tmp != null){
            tmp.setAdresse(adresse);
            em.merge(tmp);
        }
    }

    @Transactional
    @Override
    public void updateAdresse(long kundeNummer, Adresse neueAdresse) {
        Kunde tmp = em.find(Kunde.class, kundeNummer);
        if (tmp != null){
            tmp.setAdresse(neueAdresse);
            em.merge(tmp);
        }
    }

    @Override
    public Adresse getAdresse(long kundeNummer) {
        Kunde kunde = em.find(Kunde.class, kundeNummer);
        if (kunde == null)
            return null;
        return kunde.getAdresse();
    }

    @Transactional
    @Override
    public boolean deleteAdresse(long kundeNummer) {
        Kunde tmp = em.find(Kunde.class, kundeNummer);
        if (tmp != null){
            tmp.deleteAdresse();
            em.merge(tmp);
            return true;
        }
        return false;
    }
    
}
