package com.ensta.myfilmlist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.myfilmlist.exception.DaoException;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import com.ensta.myfilmlist.pojo.RealisateurPojo;

import org.springframework.stereotype.Repository;

@Repository
public class RealisateurDAO {
    private static final String FIND_REALS_QUERY = "SELECT id, nom, prenom, date_naissance FROM Realisateur;";
    private static final String FIND_REALS_FILTER_QUERY = "SELECT id, nom, prenom, date_naissance FROM Realisateur WHERE (nom LIKE ? OR prenom LIKE ?);";
    private static final String COUNT_REALS_QUERY = "SELECT COUNT(*) FROM Realisateur;";
    private static final String FIND_REAL_QUERY = "SELECT id, nom, prenom, date_naissance FROM Realisateur WHERE id = ?;";
	private static final String DELETE_QUERY = "DELETE FROM Realisateur WHERE id = ?;";
	private static final String CREATE_QUERY = "INSERT INTO Realisateur(nom, prenom, date_naissance) VALUES(? ,? ,? )";
	private static final String UPDATE_QUERY = "UPDATE Realisateur SET nom = ?, prenom = ?, date_naissance = ? WHERE id = ?;";

	/**
	 * Retourne tous les realisateurs de la BDD 
	 * @return liste des realisateurs de la BDD (List<RealisateurPojo>)
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public List<RealisateurPojo> findAll() throws DaoException {
		List<RealisateurPojo> resultList = new ArrayList<>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_REALS_QUERY);) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				RealisateurPojo realisateur = new RealisateurPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));

				resultList.add(realisateur);
			}
			return resultList;

		} catch (SQLException e) {
			throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
		}
	}
	
	/**
	 * Compte tous les reals de la BDD 
	 * @return nombre de reals dans la bdd (int)
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public long countReals() throws DaoException {
		long total = 0;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(COUNT_REALS_QUERY);) {

			ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			total = resultSet.getInt(1);
		}					
		return total;
		} catch (SQLException e) {
			throw new DaoException("Erreur lors du COUNT : " + e.getMessage());
		}
	}

	/**
	 * Retourne un realisateur à partir de son id
	 * @return liste des realisateurs de la BDD (List<RealisateurPojo>)
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public RealisateurPojo findById(String id) throws DaoException {
		RealisateurPojo realisateur = new RealisateurPojo();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_REAL_QUERY);) {
			
			statement.setInt(1, Integer.parseInt(id));
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				realisateur.setId(resultSet.getInt(1));
				realisateur.setNom(resultSet.getString(2));
                realisateur.setPrenom(resultSet.getString(3));
				realisateur.setDate(resultSet.getDate(4));

			}
			return realisateur;

		} catch (SQLException e) {
			throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
		}
	}

	/**
	 * Retourne tous les realisateurs de la BDD 
	 * @return liste des realisateurs de la BDD (List<RealisateurPojo>)
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public List<RealisateurPojo> findWithFilter(String real) throws DaoException {
		List<RealisateurPojo> resultList = new ArrayList<>();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_REALS_FILTER_QUERY);) {

			statement.setString(1, '%' + real + '%');
			statement.setString(2, '%' + real + '%');

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				RealisateurPojo realisateur = new RealisateurPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));

				resultList.add(realisateur);
			}
			return resultList;

		} catch (SQLException e) {
			throw new DaoException("Erreur lors du SELECT : " + e.getMessage());
		}
	}

	/**
	 * Crée un realisateur dans la BDD 
	 * @return void
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public void create(RealisateurPojo realisateur) throws DaoException {
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_QUERY);) {
					statement.setString(1, realisateur.getNom());
					statement.setString(2, realisateur.getPrenom());
					statement.setDate(3, realisateur.getDate());
					statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors du INSERT : " + e.getMessage());
		}
	}

	/**
	 * Update un realisateur dans la BDD 
	 * @return void
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public Integer update(RealisateurPojo realisateur) throws DaoException {
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);) {
					statement.setString(1, realisateur.getNom());
					statement.setString(2, realisateur.getPrenom());
					statement.setDate(3, realisateur.getDate());
					statement.setInt(4, (int) realisateur.getId());
					return statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors du UPDATE : " + e.getMessage());
		}
	}


	/**
	 * Retourne tous les realisateurs de la BDD 
	 * @return liste des realisateurs de la BDD (List<RealisateurPojo>)
	 * @throws DaoException Une erreur est survenue lors de la connexion à la BDD
	 */
    public String delete(String id) throws DaoException {
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);) {
					statement.setInt(1, Integer.parseInt(id));
					statement.executeUpdate();
					return id;
		} catch (SQLException e) {
			throw new DaoException("Erreur lors du DELETE : " + e.getMessage());
		}
	}
}