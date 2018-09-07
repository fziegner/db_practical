package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Company extends Organization {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	
	//@OneToMany(mappedBy="company")
	//private List<workAt> workat = new ArrayList<workAt>();
	
	public Company() {		
	}

	public Company(int companyid, Country country, List<workAt> workat) {
		this.country = country;
		//this.workat = workat;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	//public List<workAt> getWorkat() {
		//return workat;
	//}

	//public void setWorkat(List<workAt> workat) {
		//this.workat = workat;
	//}
}