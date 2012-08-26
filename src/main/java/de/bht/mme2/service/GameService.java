package de.bht.mme2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import de.bht.mme2.domain.Game;

@Service
public class GameService {

	private Logger logger = LoggerFactory.getLogger(PersonService.class);

	public ModelAndView buildListView(List<Game> games) {
		
		//empty Person Object to create new Person
		Game newGame = new Game();
		
		ModelAndView mv = new ModelAndView("Person");
		mv.addObject("Game", newGame);
		mv.addObject("Message:", "Es wurden " + games.size() + " Games aus der DB gelesen.");
		mv.addObject("Games", games);
		
		return mv;
	}
	
	public Game findGameByID(List<Game> games, long id){
		for(Game currentGame : games) {
			if( currentGame.getID() == id) {
				return currentGame;
			}
		}
		return null;
	}
	
	
}
