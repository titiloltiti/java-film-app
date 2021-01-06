package com.ensta.myfilmlist.service;

import java.util.List;

import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.RealisateurMapper;
import com.ensta.myfilmlist.model.Realisateur;

import org.springframework.stereotype.Service;

import jdk.nashorn.internal.ir.ReturnNode;

@Service
public class RealisateurService {

    private RealisateurDAO realisateurDAO;

    public RealisateurService(RealisateurDAO realisateurDAO) {
        this.realisateurDAO = realisateurDAO;
    }

    /**
     * Récupère la liste des realisateurs en List<RealisateurPojo>, l'envoie au mapper pour récupérer la liste en modèle, puis renvoie ce modèle au mapper pour recevoir la liste en List<RealisateurDTO> qui est finalement retournée
     * @return la liste de tous les realisateurs au format List<RealisateurDTO>
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public List<RealisateurDTO> findAll() throws ServiceException {
        List<Realisateur> listRealisateurModel;
        try {
            listRealisateurModel = RealisateurMapper.listRealisateurPojoToListRealisateur(realisateurDAO.findAll());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return RealisateurMapper.listRealisateurToListRealisateurDTO(listRealisateurModel);
    }

    /**
     * renvoie le nombre total de films
     * @return le nombre total de films
     * @throws ServiceExceptionproblème lors de l'accès au DAO
     */
    public long countReals() throws ServiceException {
        long total; 
        try {
            total =  realisateurDAO.countReals();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return total;
    }

    /**
     * Récupère un realisateur a partir de son ID
     * @return le realisateur cherché
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public RealisateurDTO findById(String id) throws ServiceException {
        Realisateur realisateurModel;
        try {
            realisateurModel = RealisateurMapper.realisateurPojoToRealisateur(realisateurDAO.findById(id));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return RealisateurMapper.realisateurToRealisateurDTO(realisateurModel);
    }

    /**
     * Récupère la liste filtrée des realisateurs en List<RealisateurPojo>, l'envoie au mapper pour récupérer la liste en modèle, puis renvoie ce modèle au mapper pour recevoir la liste en List<RealisateurDTO> qui est finalement retournée
     * @return la liste filtrée des realisateurs au format List<RealisateurDTO>
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public List<RealisateurDTO> findWithFilter(String real) throws ServiceException {
        List<Realisateur> listRealisateurModel;
        try {
            listRealisateurModel = RealisateurMapper.listRealisateurPojoToListRealisateur(realisateurDAO.findWithFilter(real));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return RealisateurMapper.listRealisateurToListRealisateurDTO(listRealisateurModel);
    }

    /**
     * Créer un realisateur dans la BDD
     * @return le realisateur crée
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public void create(RealisateurDTO realisateur) throws ServiceException {
        try {
           realisateurDAO.create(RealisateurMapper.realisateurToRealisateurPojo(RealisateurMapper.realisateurDTOToRealisateur(realisateur)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Supprime un realisateur de la BDD
     * @return le realisateur supprimé
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public String delete(String id) throws ServiceException {
        try {
           String returnId = realisateurDAO.delete(id);
           return returnId;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    
    /**
     * Update un realisateur dans la BDD
     * @return le realisateur mis à jour
     * @throws ServiceException problème lors de l'accès au DAO
     */
    public Integer update(RealisateurDTO realisateur) throws ServiceException {
        try {
           return realisateurDAO.update(RealisateurMapper.realisateurToRealisateurPojo(RealisateurMapper.realisateurDTOToRealisateur(realisateur)));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}