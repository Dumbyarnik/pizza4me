package de.hsos.sportteam_api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Dependent
public class Kunde implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = true, unique = true)
    private String nachname;

    @Column(nullable = true)
    private String vorname;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @OneToMany(mappedBy = "kunde", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Collection<Bestellung> bestellungen = new ArrayList<Bestellung>();

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

    /**
     * @return String return the vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @param vorname the vorname to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * @return Collection<Bestellung> return the bestellungen
     */
    public Collection<Bestellung> getBestellungen() {
        return bestellungen;
    }

    /**
     * @param bestellungen the bestellungen to set
     */
    public void setBestellungen(Collection<Bestellung> bestellungen) {
        this.bestellungen = bestellungen;
    }

    public void addBestellung(Bestellung bestellung){
        bestellungen.add(bestellung);
    }

}
