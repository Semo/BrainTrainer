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

import de.bht.mme2.dao.PersonDao;
import de.bht.mme2.domain.Person;
import de.bht.mme2.service.PersonService;

@Controller
@RequestMapping("/person/")
public class PersonController {

	private Logger logger = LoggerFactory.getLogger(PersonController.class);

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

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Person addPerson(@RequestBody Person person) {
		logger.info("Person angelegt: " + person.toString());
		personDao.addPerson(person);
		return person;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deletePerson(@RequestBody long id) {
		logger.info("Lösche Person mit ID: " + id);
		personDao.deletePerson(id);
	}
}