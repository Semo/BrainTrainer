package de.bht.mme2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.bht.mme2.domain.Person;
import de.bht.mme2.service.PersonService;

@Repository
public class PersonDao {

	@Autowired
	private PersonService personService;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void addPerson(Person person) {
		entityManager.merge(person);
	}

	@Transactional
	public Boolean deletePerson(long id) {
		Person deleteMe = personService.findPersonByID(this.getAllPersons(), id);
		if (deleteMe != null) {
			entityManager.remove(deleteMe);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		Query q = entityManager.createQuery("select * from Person;");
		return q.getResultList();
	}

}