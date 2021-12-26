package de.hsos.sportteam_api.entities.DAO;

public class BestellpostMinDAO {
    public Long pizzaId;
    public Integer pizzaMenge;

    public BestellpostMinDAO(){}

    public BestellpostMinDAO(Long pizzaId, Integer pizzaMenge){
        this.pizzaId = pizzaId;
        this.pizzaMenge = pizzaMenge;
    }
}
