package de.hsos.sportteam_api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Dependent
public class Bestellung implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "bestellung", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Collection<Bestellpost> bestellposten = new ArrayList<Bestellpost>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "kunde_id", nullable = false)
    private Kunde kunde;

    public Bestellung(){}

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return ArrayList<Bestellpost> return the bestellposten
     */
    public Collection<Bestellpost> getBestellposten() {
        return bestellposten;
    }

    /**
     * @param bestellposten the bestellposten to set
     */
    public void setBestellposten(Collection<Bestellpost> bestellposten) {
        this.bestellposten = bestellposten;
    }

    public void addBestellpost(Bestellpost bestellpost){
        this.bestellposten.add(bestellpost);
    }

    public void deleteBestellposten(){
        this.bestellposten = null;
    }

    public Kunde getKunde(){
        return kunde;
    }

    public void setKunde(Kunde kunde){
        this.kunde = kunde;
    }

    public void deleteKunde(){
        this.kunde = null;
    }

}
