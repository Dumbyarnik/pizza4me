package de.hsos.sportteam_api.gateway;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.Bestellpost;
import de.hsos.sportteam_api.entities.Bestellung;
import de.hsos.sportteam_api.entities.Kunde;

@Model
public class BestellungRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    @Transactional
    public boolean createBestellung(Long kunde_id){
        Kunde kunde = em.find(Kunde.class, kunde_id);
        if (kunde == null)
            return false;

        Bestellung bestellung = new Bestellung();
        bestellung.setKunde_id(kunde_id);
        em.persist(bestellung);
        return true;
    }

    public Collection<Bestellung> getBestellungen() {
        Collection<Bestellung> bestellungen = 
            em.createQuery("SELECT b FROM Bestellung b", Bestellung.class).getResultList();
        for (Bestellung bestellung : bestellungen) {
            for (Bestellpost bestellpost : bestellung.getBestellposten()) {
                bestellpost.deleteBestellung();
            }
        }
        return bestellungen;
    }

    public Collection<Bestellpost> getBestellposten(Long bestellung_id){
        Bestellung bestellung = em.find(Bestellung.class, bestellung_id);
        if (bestellung == null)
            return null;
        Collection<Bestellpost> bestellposten = bestellung.getBestellposten();
        for (Bestellpost bestellpost : bestellposten) {
            bestellpost.deleteBestellung();
        }
        return bestellposten;
    }

    @Transactional
    public boolean createBestellpost(Long bestellung_id) {
        Bestellung bestellung = em.find(Bestellung.class, bestellung_id);
        if (bestellung == null)
            return false;
        
        Bestellpost bestellpost = new Bestellpost();
        bestellung.addBestellpost(bestellpost);
        bestellpost.setBestellung(bestellung);
        em.merge(bestellung);
        return true;
        
    }
    
}
