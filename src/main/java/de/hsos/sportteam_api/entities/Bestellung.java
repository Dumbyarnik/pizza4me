package de.hsos.sportteam_api.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Dependent
@Entity
@Vetoed
public class Bestellung implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long kunde_id; 

    @OneToMany(mappedBy = "bestellung", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private ArrayList<Bestellpost> bestellposten;

    public Bestellung(){}

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Long return the kunde_id
     */
    public Long getKunde_id() {
        return kunde_id;
    }

    /**
     * @param kunde_id the kunde_id to set
     */
    public void setKunde_id(Long kunde_id) {
        this.kunde_id = kunde_id;
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
    public ArrayList<Bestellpost> getBestellposten() {
        return bestellposten;
    }

    /**
     * @param bestellposten the bestellposten to set
     */
    public void setBestellposten(ArrayList<Bestellpost> bestellposten) {
        this.bestellposten = bestellposten;
    }

}
