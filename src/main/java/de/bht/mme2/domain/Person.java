package de.bht.mme2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed(index = "hibernate/index")
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Forename")
	private String firstName;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	@Column(name = "Surname")
	private String lastname;

	@Column(name = "Birthdate")
	private String birthDate;

	@Column(name = "TotalScore")
	private Integer totalScore;

	@Column(name = "ReactionTime")
	private Integer reactionTime;

	@Column(name = "Visits")
	private Integer visits;

	public Person(String gender, String firstName, String lastname, String birthDate, Integer totalScore,
			Integer reactionTime, Integer visits) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.totalScore = totalScore;
		this.reactionTime = reactionTime;
		this.visits = visits;
	}

	public Person() {}
	
	public long getID() {
		return id;
	}

	public final String getGender() {
		return gender;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final String getLastname() {
		return lastname;
	}

	public final String getBirthDate() {
		return birthDate;
	}

	public final int getTotalScore() {
		return totalScore;
	}

	public final int getReactionTime() {
		return reactionTime;
	}

	public final int getVisits() {
		return visits;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public final void setGender(String gender) {
		this.gender = gender;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public final void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public final void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public final void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}

	public final void setVisits(int visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", gender=" + gender + ", firstName=" + firstName + ", lastname=" + lastname
				+ ", birthDate=" + birthDate + ", totalScore=" + totalScore + ", reactionTime=" + reactionTime
				+ ", visits=" + visits + "]";
	}

}