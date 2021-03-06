package de.bht.mme2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.bht.mme2.domain.Game;
import de.bht.mme2.service.GameService;

@Repository
@Transactional
public class GameDao {

	//Launchpad PWD LKJBSzu567D
	
	@Autowired
	private GameService gameService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void addGame(Game game) {
		entityManager.merge(game);
	}

	@Transactional
	public Boolean deleteGame(Long id) {
		Game deleteMe = gameService.findGameByID(this.getAllGames(), id);
		if (deleteMe != null) {
			entityManager.remove(deleteMe);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Game> getAllGames() {
		Query q = entityManager.createQuery("from Game");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Game> getActiveGames() {
		Query q = entityManager.createQuery("from Game where isActive = true");
		return q.getResultList();
	}

	public Game getGameById(Long id) {
		return gameService.findGameByID(this.getAllGames(), id);
	}
}
