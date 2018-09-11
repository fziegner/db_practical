package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class University extends Organization {

	@ManyToOne
	@JoinColumn(name = "city")
	private City city;
	
	@OneToMany(mappedBy="university")
	private List<StudyAt> studyat;

	public University() {
	}

	public University(int universityid, City city, List<StudyAt> studyat) {
		this.city = city;
		this.studyat = studyat;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<StudyAt> getStudyat() {
		return studyat;
	}

	public void setStudyat(List<StudyAt> studyat) {
		this.studyat = studyat;
	}
}