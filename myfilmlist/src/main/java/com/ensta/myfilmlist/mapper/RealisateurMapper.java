package com.ensta.myfilmlist.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.pojo.RealisateurPojo;

public class RealisateurMapper {

    /**
     * Crée un RealisateurDTO à partir du parametre realisateur
     * @param realisateur
     * @return le RealisateurDTO crée
     */
    public static RealisateurDTO realisateurToRealisateurDTO(Realisateur realisateur){
        return new RealisateurDTO(realisateur.getId(),realisateur.getNom(), realisateur.getPrenom(),realisateur.getDate());
    }

     /**
     * Crée un Realisateur à partir du parametre realisateurDTO
     * @param realisateur
     * @return le Realisateur crée
     */
    public static Realisateur realisateurDTOToRealisateur(RealisateurDTO realisateur){
        return new Realisateur(realisateur.getId(),realisateur.getNom(), realisateur.getPrenom(),realisateur.getDate());
    }

    /**
     * Crée une liste de RealisateurDTO à partir d'une liste de realisateurs donnée en paramètre
     * @param listRealisateur
     * @return la liste de RealisateurDTO crées
     */
    public static List<RealisateurDTO> listRealisateurToListRealisateurDTO(List<Realisateur> listRealisateur){
        List<RealisateurDTO> listRealisateurDTO = new ArrayList<>();
        for (Realisateur realisateur : listRealisateur ){
            listRealisateurDTO.add(realisateurToRealisateurDTO(realisateur));
        }
        return listRealisateurDTO;
    }

    /**
     * Crée un Realisateur a partir du RealisateurPojo en paramètre
     * @param realisateur
     * @return le Realisateur crée
     */
    public static Realisateur realisateurPojoToRealisateur(RealisateurPojo realisateur){
        return new Realisateur(realisateur.getId(),realisateur.getNom(), realisateur.getPrenom(),realisateur.getDate());
    }

    /**
     * Crée un Realisateur a partir du RealisateurPojo en paramètre
     * @param realisateur
     * @return le Realisateur crée
     */
    public static RealisateurPojo realisateurToRealisateurPojo(Realisateur realisateur){
        return new RealisateurPojo(realisateur.getId(),realisateur.getNom(), realisateur.getPrenom(),realisateur.getDate());
    }



    /**
     * Crée une liste de Realisateur à partir d'une List<RealisateurPojo> en paramètre
     * @param listRealisateur
     * @return la List<Realisateur> crée
     */
    public static List<Realisateur> listRealisateurPojoToListRealisateur(List<RealisateurPojo> listRealisateur){
        List<Realisateur> listRealisateurDTO = new ArrayList<>();
        for (RealisateurPojo realisateur : listRealisateur ){
            listRealisateurDTO.add(realisateurPojoToRealisateur(realisateur));
        }
        return listRealisateurDTO;
    }

}