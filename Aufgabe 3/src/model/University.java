package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class University extends Organization {

	@Id
	private int universityid;
	
	@ManyToOne
	@JoinColumn(name = "city")
	private City city;
	
	@OneToMany(mappedBy="university")
	private List<studyAt> studyat = new ArrayList<studyAt>();

	public University() {
	}

	public University(int universityid, City city, List<studyAt> studyat) {
		this.universityid = universityid;
		this.city = city;
		this.studyat = studyat;
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

	public List<studyAt> getStudyat() {
		return studyat;
	}

	public void setStudyat(List<studyAt> studyat) {
		this.studyat = studyat;
	}
}