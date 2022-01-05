package de.hsos.sportteam_api.gateway.bestellung;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.DAO.BestellpostMinDAO;
import de.hsos.sportteam_api.entities.basic.Bestellpost;
import de.hsos.sportteam_api.entities.basic.Bestellung;
import de.hsos.sportteam_api.entities.basic.Kunde;
import de.hsos.sportteam_api.entities.basic.Pizza;

@Model
@Dependent
public class BestellungRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    @Transactional
    public long createBestellung(String username, BestellpostMinDAO bestellpostDAO){
        Kunde kunde = em.createQuery("Select k FROM Kunde k where " + 
            "k.nachname LIKE :username",
            Kunde.class)
            .setParameter("username", username)
            .getSingleResult();
        
        if (kunde == null)
            return 0;
        Pizza pizza = em.find(Pizza.class, bestellpostDAO.pizzaId);
        if (pizza == null)
            return 0;

        Bestellung bestellung = new Bestellung();
        // creating bestellpost
        Bestellpost bestellpost = new Bestellpost();
        bestellpost.setPizza(pizza);
        bestellpost.setMenge(bestellpostDAO.pizzaMenge);
        // creating relationships
        bestellung.addBestellpost(bestellpost);
        bestellung.setKunde(kunde);
        bestellpost.setBestellung(bestellung);
        
        em.persist(bestellung);
        em.flush();

        return bestellung.getId();
    }

    public Collection<Bestellung> getBestellungen() {
        Collection<Bestellung> bestellungen = 
            em.createQuery("SELECT b FROM Bestellung b", Bestellung.class).getResultList();
        for (Bestellung bestellung : bestellungen) {
            for (Bestellpost bestellpost : bestellung.getBestellposten()) {
                bestellpost.deleteBestellung();
            }
            bestellung.deleteKunde();
        }
        return bestellungen;
    }
}
