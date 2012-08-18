package de.bht.mme2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.bht.mme2.dao.PersonDao;
import de.bht.mme2.domain.Person;
import de.bht.mme2.service.PersonService;

@Controller
@RequestMapping
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/person/add")
	public String addPerson(@ModelAttribute("person") Person person, BindingResult result){
		logger.info("Person angelegt: " + person.toString());
		personDao.addPerson(person);
		return "redirect:/";
	}
	
	
	
}