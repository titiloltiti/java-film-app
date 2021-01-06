package com.ensta.myfilmlist.dto;

import java.sql.Date;

import com.ensta.myfilmlist.model.Realisateur;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RealisateurDTO {
    private long id;
    private String nom;
    private String prenom;
    private Date date; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
  
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    } 

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    } 

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    } 

    public RealisateurDTO() {};
    
    public RealisateurDTO(String nom, String prenom, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    public RealisateurDTO(long id,String nom, String prenom, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

}