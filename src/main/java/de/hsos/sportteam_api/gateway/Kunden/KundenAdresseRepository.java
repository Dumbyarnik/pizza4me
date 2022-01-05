package de.hsos.sportteam_api.gateway.Kunden;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.sportteam_api.entities.Adresse;
import de.hsos.sportteam_api.entities.Bestellung;
import de.hsos.sportteam_api.entities.Kunde;
import de.hsos.sportteam_api.entities.DAO.AdresseDAO;
import de.hsos.sportteam_api.entities.security.UserLogin;

@Model
@Dependent
public class KundenAdresseRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    @Transactional
    public void createAdresse(String username, AdresseDAO adresseDAO) {
        Kunde kunde = em.createQuery("Select k FROM Kunde k where " + 
            "k.nachname LIKE :username",
            Kunde.class)
            .setParameter("username", username)
            .getSingleResult();

        if (kunde != null){
            Adresse adresse = new Adresse();
            adresse.setHausnr(adresseDAO.hausnr);
            adresse.setOrt(adresseDAO.ort);
            adresse.setPlz(adresseDAO.plz);
            adresse.setStrasse(adresseDAO.strasse);

            kunde.setAdresse(adresse);
            em.merge(kunde);
        }
    }

    @Transactional
    public void updateAdresse(String username, AdresseDAO neueAdresseDAO) {
        Kunde kunde = em.createQuery("Select k FROM Kunde k where " + 
            "k.nachname LIKE :username",
            Kunde.class)
            .setParameter("username", username)
            .getSingleResult();

        if (kunde != null){
            Adresse neueAdresse = new Adresse();
            neueAdresse.setHausnr(neueAdresseDAO.hausnr);
            neueAdresse.setOrt(neueAdresseDAO.ort);
            neueAdresse.setPlz(neueAdresseDAO.plz);
            neueAdresse.setStrasse(neueAdresseDAO.strasse);

            kunde.setAdresse(neueAdresse);
            em.merge(kunde);
        }
    }

    public Adresse getAdresse(String username) {
        Kunde kunde = em.createQuery("Select k FROM Kunde k where " + 
            "k.nachname LIKE :username",
            Kunde.class)
            .setParameter("username", username)
            .getSingleResult();
        if (kunde == null)
            return null;
        return kunde.getAdresse();
    }

    @Transactional
    public boolean deleteAdresse(String username) {
        Kunde kunde = em.createQuery("Select k FROM Kunde k where " + 
            "k.nachname LIKE :username",
            Kunde.class)
            .setParameter("username", username)
            .getSingleResult();
        
        if (kunde != null){
            kunde.deleteAdresse();
            em.merge(kunde);
            return true;
        }
        return false;
    }
    
}
