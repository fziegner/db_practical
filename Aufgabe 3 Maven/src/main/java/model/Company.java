package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Company extends Organization {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy = "company")
	private List<WorkAt> workAt;

	public Company() {		
	}

	public Company(Country country, List<WorkAt> workAt) {
		this.country = country;
		this.workAt = workAt;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<WorkAt> getWorkAt() {
		return workAt;
	}

	public void setWorkAt(List<WorkAt> workAt) {
		this.workAt = workAt;
	}
}