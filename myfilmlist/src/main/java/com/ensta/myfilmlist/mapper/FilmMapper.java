package com.ensta.myfilmlist.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.pojo.FilmPojo;
import com.ensta.myfilmlist.service.RealisateurService;

public class FilmMapper {
    /**
     * Crée un FilmDTO à partir du parametre film
     * @param film
     * @return le FilmDTO crée
     */
    public static FilmDTO filmToFilmDTO(Film film, RealisateurDTO realisateur){
        return new FilmDTO(film.getId(),film.getTitre(), film.getDuration(),film.getRealisateurID(),realisateur.getPrenom()+" "+realisateur.getNom());
    }

     /**
     * Crée un Film à partir du parametre filmDTO
     * @param film
     * @return le Film crée
     */
    public static Film filmDTOToFilm(FilmDTO film){
        return new Film(film.getId(),film.getTitre(), film.getDuration(),film.getRealisateurID());
    }

    /**
     * Crée une liste de FilmDTO à partir d'une liste de films donnée en paramètre
     * @param listFilm
     * @return la liste de FilmDTO crées
     */
    public static List<FilmDTO> listFilmToListFilmDTO(List<Film> listFilm, List<RealisateurDTO> listRealisateurDTO){
        List<FilmDTO> listFilmDTO = new ArrayList<>();
        for (Film film : listFilm ){
            int realisateurID = film.getRealisateurID(); 
            for (int pos = 0; pos < listRealisateurDTO.size(); pos++) {
                if (realisateurID == listRealisateurDTO.get(pos).getId()) {
                    listFilmDTO.add(filmToFilmDTO(film,listRealisateurDTO.get(pos)));
                } 
            }
        }
        return listFilmDTO;
    }

    /**
     * Crée un Film a partir du FilmPojo en paramètre
     * @param film
     * @return le Film crée
     */
    public static Film filmPojoToFilm(FilmPojo film){
        return new Film(film.getId(),film.getTitre(), film.getDuration(),film.getRealisateurID());
    }

    /**
     * Crée un FilmPojo a partir du Film en paramètre
     * @param film
     * @return le Film crée
     */
    public static FilmPojo filmToFilmPojo(Film film){
        return new FilmPojo(film.getId(),film.getTitre(), film.getDuration(),film.getRealisateurID());
    }



    /**
     * Crée une liste de Film à partir d'une List<FilmPojo> en paramètre
     * @param listFilm
     * @return la List<Film> crée
     */
    public static List<Film> listFilmPojoToListFilm(List<FilmPojo> listFilm){
        List<Film> listFilmDTO = new ArrayList<>();
        for (FilmPojo film : listFilm ){
            listFilmDTO.add(filmPojoToFilm(film));
        }
        return listFilmDTO;
    }

}