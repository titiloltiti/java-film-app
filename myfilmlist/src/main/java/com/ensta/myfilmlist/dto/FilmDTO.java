package com.ensta.myfilmlist.dto;

import com.ensta.myfilmlist.model.Film;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FilmDTO {
    private long id;
    private String titre;
    private int duration;
    private int realisateurID;
    private String realisateurName;

    public String getTitre() {
        return titre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public int getRealisateurID() {
        return realisateurID;
    }

    public void setRealisateurID(int realisateurID) {
        this.realisateurID = realisateurID;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRealisateurName() {
        return realisateurName;
    }

    public void setRealisateurName(String realisateurName) {
        this.realisateurName = realisateurName;
    }

    public FilmDTO() {};
    
    public FilmDTO(String titre, int duration, int realisateurID, String realisateurName) {
        this.titre = titre;
        this.duration = duration;
        this.realisateurID = realisateurID;
        this.realisateurName = realisateurName;
    }

    public FilmDTO(long id, String titre, int duration, int realisateurID, String realisateurName) {
        this.id = id;
        this.titre = titre;
        this.duration = duration;
        this.realisateurID = realisateurID;
        this.realisateurName = realisateurName;
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