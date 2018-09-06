package model;

import javax.persistence.*;

@Entity
public class Company extends Organization {

	@Id
	private int companyid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	
	public Company() {		
	}
	
	public Company(int id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}