package com.ensta.myfilmlist.service;

import java.util.List;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.mapper.RealisateurMapper;
import com.ensta.myfilmlist.model.Film;

import org.springframework.stereotype.Service;

import jdk.nashorn.internal.ir.ReturnNode;

@Service
public class FilmService {

    private FilmDAO filmDAO;

    private RealisateurService realisateurService;

    public FilmService(FilmDAO filmDAO, RealisateurService realisateurService) {
        this.filmDAO = filmDAO;
        this.realisateurService = realisateurService;
    }

    /**
     * Récupère la liste des films en List<FilmPojo>, l'envoie au mapper pour récupérer la liste en modèle, puis renvoie ce modèle au mapper pour recevoir la liste en List<FilmDTO> qui est finalement retournée
     * @return la liste de tous les films au format List<FilmDTO>
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public List<FilmDTO> findAll(int number,int size) throws ServiceException {
        List<Film> listFilmModel;
        List<RealisateurDTO> listRealisateurDTO;
        try {
            listFilmModel = FilmMapper.listFilmPojoToListFilm(filmDAO.findAll(number,size));
            listRealisateurDTO = realisateurService.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return FilmMapper.listFilmToListFilmDTO(listFilmModel,listRealisateurDTO);
    }

    /**
     * renvoie le nombre total de pages
     * @param size la taille d'une page (int)
     * @return le nombre total de pages
     * @throws ServiceExceptionproblème lors de l'accès au DAO
     */
    public long getTotal(int size) throws ServiceException {
        long total; 
        try {
            total =  filmDAO.countFilms()/size + 1;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return total;
    }

    /**
     * renvoie le nombre total de films
     * @return le nombre total de films
     * @throws ServiceExceptionproblème lors de l'accès au DAO
     */
    public long countFilms() throws ServiceException {
        long total; 
        try {
            total =  filmDAO.countFilms();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return total;
    }

    /**
     * renvoie le nombre total de pages avec un filtre
     * @param size la taille d'une page (int)
     * @return le nombre total de pages
     * @throws ServiceExceptionproblème lors de l'accès au DAO
     */
    public long getTotalWithFilter(String titre, String real, int size) throws ServiceException {
        long total; 
        try {
            total = filmDAO.countFilmsFiltered(titre,real)/size + 1 ;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return total;
    }

    /**
     * Récupère un film a partir de son ID
     * @return le film cherché
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public FilmDTO findById(String id) throws ServiceException {
        Film filmModel;
        RealisateurDTO realisateurDTO;
        try {
            filmModel = FilmMapper.filmPojoToFilm(filmDAO.findById(id));
            realisateurDTO = realisateurService.findById(Integer.toString(filmModel.getRealisateurID()));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return FilmMapper.filmToFilmDTO(filmModel, realisateurDTO);
    }

    /**
     * Récupère une liste de films en fonction du filtre (titre,real)
     * @return la liste de films
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public List<FilmDTO> findWithFilter(String titre, String real, int number, int size) throws ServiceException {
        List<Film> listFilmModel;
        List<RealisateurDTO> listRealisateurDTO;
        try {
            listFilmModel = FilmMapper.listFilmPojoToListFilm(filmDAO.findWithFilter(titre, real, number, size));
            listRealisateurDTO = realisateurService.findAll(); 
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return FilmMapper.listFilmToListFilmDTO(listFilmModel,listRealisateurDTO);
    }
    /**
     * Créer un film dans la BDD
     * @return le film crée
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public void create(FilmDTO film) throws ServiceException {
        try {
           filmDAO.create(FilmMapper.filmToFilmPojo(FilmMapper.filmDTOToFilm(film)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Supprime un film de la BDD
     * @return le film supprimé
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public String delete(String id) throws ServiceException {
        try {
           String returnId = filmDAO.delete(id);
           return returnId;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    
    /**
     * Update un film dans la BDD
     * @return le film mis à jour
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public Integer update(FilmDTO film) throws ServiceException {
        try {
           return filmDAO.update(FilmMapper.filmToFilmPojo(FilmMapper.filmDTOToFilm(film)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}