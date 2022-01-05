package de.hsos.sportteam_api.gateway.kunden;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.DAO.AdresseDAO;
import de.hsos.sportteam_api.entities.basic.Adresse;
import de.hsos.sportteam_api.entities.basic.Bestellung;
import de.hsos.sportteam_api.entities.basic.Kunde;
import de.hsos.sportteam_api.entities.security.UserLogin;

@Model
@Dependent
public class KundenRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    @Transactional
    public void persistKunde (Kunde kunde){
        em.persist(kunde);
    }

    @Transactional
    public boolean createKunde(String name, String password) {
        Kunde tmp = new Kunde(name);
        try {
            UserLogin.add(name, password, "KundIn");
            em.persist(tmp);
        }
        catch (Exception e){
            return false;
        }
        return true;        
    }

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

    public Kunde getKunde(long kundeNummer) {
        Kunde kunde = em.find(Kunde.class, kundeNummer);
        for (Bestellung bestellung : kunde.getBestellungen()){
            bestellung.deleteKunde();
            bestellung.deleteBestellposten();
        }
        return kunde;
    }

    @Transactional
    public boolean deleteKunde(long kundeNummer) {
        Kunde tmp = em.find(Kunde.class, kundeNummer);
        if (tmp != null){
            em.remove(tmp);
            return true;
        }
        return false;
    }    
}
