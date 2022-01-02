package de.hsos.sportteam_api.entities;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
@Dependent
public class Bestellpost implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "bestellung_id", nullable = false)
    private Bestellung bestellung;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    private Pizza pizza;
    
    private Integer menge;

    public Bestellpost(){}
    

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Bestellung return the bestellung
     */
    public Bestellung getBestellung() {
        return bestellung;
    }

    /**
     * @param bestellung the bestellung to set
     */
    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }

    public void deleteBestellung(){
        this.bestellung = null;
    }


    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Pizza return the pizza
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * @param pizza the pizza to set
     */
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    /**
     * @return Integer return the menge
     */
    public Integer getMenge() {
        return menge;
    }

    /**
     * @param menge the menge to set
     */
    public void setMenge(Integer menge) {
        this.menge = menge;
    }

}
