package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class City extends Place {
	
	@OneToMany(mappedBy = "city")
	private List<Person> persons;
	
	@ManyToOne
	@JoinColumn(name = "country")
	private Country country;
	
	@OneToMany(mappedBy = "city")
	private List<University> universities;
	
	public City() {
	}

	public City(List<Person> persons, Country country, List<University> universities) {
		this.persons = persons;
		this.country = country;
		this.universities = universities;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<University> getUniversities() {
		return universities;
	}

	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}
}