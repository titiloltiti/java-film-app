package com.ensta.myfilmlist.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FilmPojo {
    private long id;
    private String titre;
    private int duration;
    private int realisateurID;

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

    public FilmPojo() {};
    
    public FilmPojo(String titre, int duration, int realisateurID) {
        this.titre = titre;
        this.duration = duration;
        this.realisateurID = realisateurID;
    }

    public FilmPojo(long id, String titre, int duration, int realisateurID) {
        this.id = id;
        this.titre = titre;
        this.duration = duration;
        this.realisateurID = realisateurID;
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