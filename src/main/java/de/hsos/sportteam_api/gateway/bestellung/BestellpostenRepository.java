package de.hsos.sportteam_api.gateway.bestellung;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.Bestellpost;
import de.hsos.sportteam_api.entities.Bestellung;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.entities.Pizza;
import de.hsos.sportteam_api.entities.DAO.BestellpostMinDAO;

@Model
@Dependent
public class BestellpostenRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

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
    public boolean createBestellpost(Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO) {
        Bestellung bestellung = em.find(Bestellung.class, bestellung_id);
        if (bestellung == null)
            return false;
        
        Pizza pizza = em.find(Pizza.class, bestellpostDAO.pizzaId);
        if (pizza == null)
            return false;
        
        Bestellpost bestellpost = new Bestellpost();
        bestellpost.setPizza(pizza);
        bestellpost.setMenge(bestellpostDAO.pizzaMenge);
        // setting relationships
        //bestellung.addBestellpost(bestellpost);
        bestellpost.setBestellung(bestellung);

        em.persist(bestellpost);
        return true;
    }
    
    @Transactional
    public boolean updateBestellpost(Long bestellung_id, 
        BestellpostMinDAO bestellpostDAO){

        Bestellung bestellung = em.find(Bestellung.class, bestellung_id);
        if (bestellung == null)
            return false;
        for (Bestellpost bestellpost : bestellung.getBestellposten())
            if (bestellpost.getPizza().getId().equals(bestellpostDAO.pizzaId)){
                bestellpost.setMenge(bestellpostDAO.pizzaMenge);
                em.merge(bestellung);

                return true;
            }
        return false;
    }

    @Transactional
    public boolean deleteBestellpost(Long bestellung_id, 
        Long pizza_id){

        Bestellung bestellung = em.find(Bestellung.class, bestellung_id);
        if (bestellung == null)
            return false;

        for (Bestellpost bestellpost : bestellung.getBestellposten())
            if (bestellpost.getPizza().getId().equals(pizza_id)){
                em.remove(bestellpost);
                return true;
            }

        return false;
    }
    
}