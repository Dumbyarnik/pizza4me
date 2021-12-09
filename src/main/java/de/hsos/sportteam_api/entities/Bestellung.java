package de.hsos.sportteam_api.entities;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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

}
