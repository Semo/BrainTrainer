package de.bht.mme2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import de.bht.mme2.domain.Person;

@Service
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	public ModelAndView buildListView(List<Person> persons) {
		
		//empty Person Object to create new Person
		Person newPerson = new Person();
		
		ModelAndView mv = new ModelAndView("Person");
		mv.addObject("Person", newPerson);
		mv.addObject("Message:", "Es wurden " + persons.size() + " Personen aus der DB gelesen.");
		mv.addObject("Persons", persons);
		
		return mv;
	}
	
	public Person findPersonByID(List<Person> persons, long id){
		for(Person currentPerson : persons) {
			if( currentPerson.getID() == id) {
				return currentPerson;
			}
		}
		return null;
	}
	
	public String convertJson(List<String> vals) {
		Gson gson = new Gson();
		logger.info("JSON-Objekt ist " + gson.toJson(vals));
		return gson.toJson(vals);
	}
	
}