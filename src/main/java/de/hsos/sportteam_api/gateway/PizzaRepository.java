package de.hsos.sportteam_api.gateway;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.basic.Pizza;

@Model
@Dependent
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

    public Pizza getPizza(Long pizza_id) {
        return em.find(Pizza.class, pizza_id);
    }

    public String getDescription(Long pizza_id) {
        Pizza pizza = em.find(Pizza.class, pizza_id);
        if (pizza == null)
            return null;
        return pizza.getDescription();
    }

    public Long getPrice(Long pizza_id) {
        Pizza pizza = em.find(Pizza.class, pizza_id);
        if (pizza == null)
            return null;
        return pizza.getPrice();
    }
    
}
