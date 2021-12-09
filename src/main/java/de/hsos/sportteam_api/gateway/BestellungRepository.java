package de.hsos.sportteam_api.gateway;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        return em.createQuery("SELECT b FROM Bestellung b", Bestellung.class).getResultList();
    }
    
}
