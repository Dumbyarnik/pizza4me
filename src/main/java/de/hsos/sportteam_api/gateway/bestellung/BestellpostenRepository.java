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
        
        // check if pizza exists already
        for (Bestellpost bestellpost : bestellung.getBestellposten())
            if (bestellpost.getPizza().getId().equals(bestellpostDAO.pizzaId)){
                return false;
            }
        
        Bestellpost bestellpost = new Bestellpost();
        // setting relationships
        bestellpost.setPizza(pizza);
        bestellpost.setMenge(bestellpostDAO.pizzaMenge);
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
                bestellung.getBestellposten().remove(bestellpost);
                bestellpost.deleteBestellung();
                bestellpost.deletePizza();
                em.merge(bestellung);
                em.remove(bestellpost);
                return true;
            }

        return false;
    }
    
}
