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
public class Kunde implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = true)
    private String nachname;

    @Column(nullable = true)
    private String vorname;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    public Kunde(){}

    public Kunde(String nachname){
        this.nachname = nachname;
    }

    public Kunde(String nachname, Adresse adresse){
        this.nachname = nachname;
        this.setAdresse(adresse);
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    public String getNachname(){
        return nachname;
    }

    public void setAdresse(Adresse neueAdresse){
        this.adresse = neueAdresse;
    }

    public Adresse getAdresse(){
        return this.adresse;
    }

    public boolean deleteAdresse(){
        if (adresse == null)
            return false;
        else
            adresse = null;
        return true;
    }

    @Override
    public String toString(){
        return "Id: " + this.id + 
            ", Nachname: " + this.nachname + 
            ", Adresse: " + this.adresse;
    }
}
