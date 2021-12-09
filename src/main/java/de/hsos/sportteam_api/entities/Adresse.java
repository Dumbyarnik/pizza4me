package de.hsos.sportteam_api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Adresse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plz;
    private String ort;
    private String strasse;
    private String hausnr;

    @OneToOne(mappedBy = "adresse")
    private Kunde kunde;

    public Adresse(){}
   
    public Adresse(String plz, String ort, String strasse, String hausnr){
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnr = hausnr;
    }

    public String getPlz(){
        return plz;
    }

    public String getOrt(){
        return ort;
    }

    public String getStrasse(){
        return strasse;
    }

    public String getHausnr(){
        return hausnr;
    }

    public void setPlz(String plz){
        this.plz = plz;
    }

    public void setOrt(String ort){
        this.ort = ort;
    }

    public void setStrasse(String strasse){
        this.strasse = strasse;
    }

    public void setHausnr(String hausnr){
        this.hausnr = hausnr;
    }
    
    public Long getId(){
        return id;
    }
}
