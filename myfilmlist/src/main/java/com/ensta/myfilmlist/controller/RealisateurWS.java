package com.ensta.myfilmlist.controller;

import java.util.List;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.service.RealisateurService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import io.swagger.annotations.ApiOperation;
// import org.slf4j.LoggerLogFactory;
// import org.slf4j.Logger;

@RestController("RealisateurWS")
@RequestMapping(value = "/realisateur")
@CrossOrigin(origins = "http://localhost:4200")
public class RealisateurWS {

    // Logger log = LoggerLogFactory.getLogger(RealisateurWS.class); // log.warn('msg')

    private RealisateurService realisateurService;

    public RealisateurWS(RealisateurService realisateurService) {
        this.realisateurService = realisateurService;
    }

    /**
     * Récupère la liste des realisateurs au format List<RealisateurDTO> retournée par le service
     * @return le statut de la requête 
     * @throws ControllerException problème lors de l'appel au service
     */
    @GetMapping
    @ApiOperation(value = "Récupère la liste des realisateurs")
    public ResponseEntity<List<RealisateurDTO>> retrieveRealisateur() throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(realisateurService.findAll());
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }


    /**
     * Compte la totalité des realisateurs de la BDD
     * @return le statut de la requête
     * @throws ControllerException problème lors de l'appel au service
     */
    @GetMapping("count")
    @ApiOperation(value = "Renvoie le nombre de realisateurs dans la BDD")
    public ResponseEntity<Long> getRealsCount() throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(realisateurService.countReals());
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }   
    }
    
    /**
     * Récupère un realisateur à partir de son id
     * @return le statut de la requête 
     * @throws ControllerException problème lors de l'appel au service
     */
    @GetMapping("{id}")
    @ApiOperation(value = "Récupère un realisateur")
    public ResponseEntity<RealisateurDTO> retrieveRealisateurById(@RequestParam String id) throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(realisateurService.findById(id));
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    // EST-CE QUE LES ID DOIVENT NECESSAIREMENT ETRE PASSEES EN STRING?

    /**
     * Crée un realisateur dans la BDD
     * @return le statut de la requête
     * @throws ControllerException problème lors de l'appel au service
     */
    @PostMapping()
    @ApiOperation(value = "Créer un realisateur dans la BDD")
    public ResponseEntity<RealisateurDTO> createRealisateur(@RequestBody RealisateurDTO realisateur) throws ControllerException {
        try {
            realisateurService.create(realisateur);
            return ResponseEntity.status(HttpStatus.OK).body(realisateur);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }   
    }

    /**
     * Supprime un realisateur de la BDD
     * @return le statut de la requête
     * @throws ControllerException problème lors de l'appel au service
     */
    @DeleteMapping()
    @ApiOperation(value = "Supprime un realisateur de la BDD")
    public ResponseEntity<String> deleteRealisateur(@RequestParam String id) throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(realisateurService.delete(id));
            // VOIR POUR METTRE UN IF AVEC UN STATUT "NOT FOUND"
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }   
    }

    /**
     * Update les champs d'un realisateur avec ceux du realisateurl donné en paramètre
     * @return le statut de la requete
     * @throws ControllerException problème lors de l'appel au service 
     */
    @PutMapping()
    @ApiOperation(value = "Update un realisateur de la BDD")
    public ResponseEntity<Integer> updateRealisateur(@RequestBody RealisateurDTO realisateur) throws ControllerException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(realisateurService.update(realisateur));
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }   
    }

}