package de.hsos.sportteam_api.entities;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Dependent
@Entity
@Vetoed
public class Pizza implements Serializable {

    // persistence variables
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Long price;

    public Pizza(){}
    
    public Pizza(String name, Long price){
        this.name = name;
        this.price = price;
    }

    public Pizza(String name, String description, Long price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Long return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Long price) {
        this.price = price;
    }

}
