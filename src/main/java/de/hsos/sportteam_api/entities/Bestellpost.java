package de.hsos.sportteam_api.entities;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Dependent
@Entity
@Vetoed
public class Bestellpost implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "bestellung_id", nullable = false)
    private Bestellung bestellung;

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

}
