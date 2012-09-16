package de.bht.mme2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.bht.mme2.dao.GameDao;
import de.bht.mme2.domain.Game;
import de.bht.mme2.service.GameService;

@Controller
@RequestMapping("/game")
public class GameController {

	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	private JsonParser parser = new JsonParser();
	private JsonObject myJsonObj = new JsonObject();
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private GameService gameService;
	
	/**
	 * Get all Games from the DB.
	 * @return JSON String containing every Game
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Game[] getAllGames() {
		List<Game> list = gameDao.getAllGames();
		logger.info("Es sind " + list.size() + " Games in der Datenbank.");
		return list.toArray(new Game[0]);
	}
	
	/**
	 * Get only active Games from the DB
	 * @return JSON String
	 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	@ResponseBody
	public Game[] getGamesDownloadable() {
		List<Game> list = gameDao.getActiveGames();
		logger.info("Es sind " + list.size() + " Games in der Datenbank.");
		return list.toArray(new Game[0]);
	}
	
	/**
	 * Get specific Game by ID
	 * @param id as Long
	 * @return JSON with single Game
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Game getGameById(@PathVariable Long id){
		Game g = gameDao.getGameById(id);
		logger.info("Game mit der ID " + id + " gefunden: " + g.toString()) ;
		return g;
	}
	
	private boolean checkForID() {
		if (myJsonObj.get("id") == null || myJsonObj.get("id").isJsonNull()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Add a new Game to the DB
	 * @param game as JSON String
	 * @return JSON with single Game
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String addGame(@RequestBody String p) {
		System.out.println(p);
		myJsonObj = (JsonObject) parser.parse(p).getAsJsonObject();
		logger.info("Game angelegt: " + p.toString());
		Game g = new Game();
		if (checkForID()) {
			g.setID(myJsonObj.get("id").getAsLong());
			System.out.println("ID was given:" + myJsonObj.get("id").getAsLong());
		}
		g.setActive(myJsonObj.get("active").getAsBoolean());
		g.setGameName(myJsonObj.get("gameName").getAsString());
		g.setFileLink(myJsonObj.get("fileLink").getAsString());
		g.setDownloads(0);
		gameDao.addGame(g);
		return "redirect:/";
	}
	
	/**
	 * Delete a specific Game by an ID
	 * @param id as Long
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteGame(@PathVariable long id) {
		logger.info("Lösche Game mit ID: " + id);
		gameDao.deleteGame(id);
		return "redirect:/game";
	}
	
}
