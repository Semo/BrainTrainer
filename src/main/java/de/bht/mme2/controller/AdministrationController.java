package de.bht.mme2.controller;

import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.bht.mme2.domain.Person;

@Controller
@RequestMapping("/admin-brain/")
public class AdministrationController {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(GameController.class);

	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;

	@SuppressWarnings("unused")
	private Person p;

	private List<Person> personList;

	@Autowired
	private GameController gameController;

	@Autowired
	private PersonController personController;

	// JSF Action definitions

	public String addPerson() {
		p = new Person("", "", "", "", 0, 0, 0);
//		personController.addPerson(p);
		form.setRendered(true);
		addCommand.setRendered(false);
		return null;
	}

	public String cancelPerson() {
		p = null;
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

	// Person related stuff

	// public String updatePerson(Person person) {
	// return createPerson(person);
	// }

	public String deletePerson(Long id) {
		personController.deletePerson(id);
		return "";
	}

	public List<Person> getAllPersons() {
		if (personList != null) {
			this.personList.clear();
		}
		Person[] p = personController.getAllPersons();
		for (int i = 0; i <= p.length; i++) {
			personList.add(p[i]);
		}
		return personList;
	}

	public Person getPersonById(Long id) {
		return personController.getPersonById(id);
	}

	// Game related stuff
	public void createGame() {
	}

	public void updateGame() {
	}

	public void deleteGame() {
	}

	public void getAllGames() {
	}

	public void getGameById() {
	}

}
