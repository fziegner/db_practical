package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class City extends Place {
	
	@Id
	@Column(name = "cityid")
	@GeneratedValue
	private int cityid;

	@OneToMany(mappedBy = "city")
	private List<Person> persons;
	
	@ManyToOne
	@JoinColumn(name = "country")
	private Country country;
	
	public City() {
	}

	public City(int cityid, List<Person> persons, Country country) {
		this.cityid = cityid;
		this.persons = persons;
		this.country = country;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
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
}