package model;

import javax.persistence.*;

public class University extends Organization {

	@Id
	private int universityid;
	
	@ManyToOne
	@JoinColumn(name = "city")
	private City city;

	public University() {
	}

	public University(int universityid, City city) {
		this.universityid = universityid;
		this.city = city;
	}

	public int getUniversityid() {
		return universityid;
	}

	public void setUniversityid(int universityid) {
		this.universityid = universityid;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}