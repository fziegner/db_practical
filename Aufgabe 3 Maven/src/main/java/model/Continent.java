package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Continent extends Place{
    
	@OneToMany(mappedBy = "continent")
	private List<Country> countries;
	
	public Continent() {
	}

	public Continent(List<Country> countries) {
		this.countries = countries;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}