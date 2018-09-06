package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Company extends Organization {

	@Id
	private int companyid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy="company")
	private List<workAt> workat = new ArrayList<workAt>();
	
	public Company() {		
	}

	public Company(int companyid, Country country, List<workAt> workat) {
		this.companyid = companyid;
		this.country = country;
		this.workat = workat;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<workAt> getWorkat() {
		return workat;
	}

	public void setWorkat(List<workAt> workat) {
		this.workat = workat;
	}
}