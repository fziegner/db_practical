package model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "continent")
public class Continent extends Place{
    
	@Column(name = "continentid")
	private int continentid;
	
	@Transient
	@OneToMany(mappedBy = "continent")
	private List<Country> countries;
	
	public Continent() {
	}

	public Continent(int continentid, List<Country> countries) {
		this.continentid = continentid;
		this.countries = countries;
	}

	public int getContinentid() {
		return continentid;
	}

	public void setContinentid(int continentid) {
		this.continentid = continentid;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}