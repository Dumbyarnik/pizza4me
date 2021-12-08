package de.hsos.sportteam_api.gateway;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.Pizza;

@Model
public class PizzaRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    @Transactional
    public void persistPizza(Pizza pizza){
        em.persist(pizza);
    }

    public Collection<Pizza> getPizzas() {
        return em.createQuery("SELECT p FROM Pizza p", Pizza.class).getResultList();
    }
    
}
