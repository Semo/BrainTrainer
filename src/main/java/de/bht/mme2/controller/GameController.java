package de.bht.mme2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.bht.mme2.dao.GameDao;
import de.bht.mme2.domain.Game;
import de.bht.mme2.service.GameService;

@Controller
@RequestMapping("/game/")
public class GameController {

	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Game[] getAllGames() {
		List<Game> list = gameDao.getAllGames();
		logger.info("Es sind " + list.size() + " Games in der Datenbank.");
		return list.toArray(new Game[0]);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Game getGameById(@PathVariable Long id){
		Game g = gameDao.getGameById(id);
		logger.info("Game mit der ID " + id + " gefunden: " + g.toString()) ;
		return g;
	}
	
	
}
