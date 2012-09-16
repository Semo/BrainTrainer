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

import de.bht.mme2.dao.PersonDao;
import de.bht.mme2.domain.Person;
import de.bht.mme2.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	private Logger logger = LoggerFactory.getLogger(PersonController.class);

	private JsonParser parser = new JsonParser();
	private JsonObject myJsonObj = new JsonObject();

	@Autowired
	private PersonDao personDao;

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Person[] getAllPersons() {
		List<Person> list = personDao.getAllPersons();
		logger.info("Es sind " + list.size() + " Personen in der Datenbank.");
		return list.toArray(new Person[0]);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person getPersonById(@PathVariable Long id) {
		logger.info("Person mit der ID " + id + " in der Datenbank gesucht.");
		Person p = personDao.getPersonById(id);
		return p;
	}

	private boolean checkForID() {
		if (myJsonObj.get("id") == null || myJsonObj.get("id").isJsonNull()) {
			return false;
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	String addPerson(@RequestBody String p) {
		System.out.println(p);
		myJsonObj = (JsonObject) parser.parse(p).getAsJsonObject();
		logger.info("Person angelegt: " + p.toString());
		Person person = new Person();
		if (checkForID()) {
			person.setId(myJsonObj.get("id").getAsLong());
			System.out.println("ID was given:" + myJsonObj.get("id").getAsLong());
		}
		person.setGender(myJsonObj.get("gender").getAsString());
		person.setFirstName(myJsonObj.get("firstName").getAsString());
		person.setLastname(myJsonObj.get("lastname").getAsString());
		person.setBirthDate(myJsonObj.get("birthDate").getAsString());
		person.setTotalScore(0);
		person.setReactionTime(0);
		person.setVisits(0);
		personDao.addPerson(person);
		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deletePerson(@PathVariable long id) {
		logger.info("Lösche Person mit ID: " + id);
		personDao.deletePerson(id);

		return "redirect:/person";
	}
}