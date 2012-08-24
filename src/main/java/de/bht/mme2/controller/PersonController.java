package de.bht.mme2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
//		System.out.println(list.toArray(new Person[0])[0]);
		return list.toArray(new Person[0]);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/person/add")
	public String addPerson(@ModelAttribute("person") Person person, BindingResult result){
		logger.info("Person angelegt: " + person.toString());
		personDao.addPerson(person);
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/person/delete/{id}")
	public String deletePerson(@PathVariable("id") long id) {
		logger.info("Lösche Person mit ID: " + id);
		personDao.deletePerson(id);
		return "redirect:/";
	}
	
}