package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class University extends Organization {

	@ManyToOne
	@JoinColumn(name = "city")
	private City city;
	
	//@OneToMany(mappedBy="university")
	//private List<studyAt> studyat = new ArrayList<studyAt>();

	public University() {
	}

	public University(int universityid, City city, List<studyAt> studyat) {
		this.city = city;
		//this.studyat = studyat;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	//public List<studyAt> getStudyat() {
		//return studyat;
	//}

	//public void setStudyat(List<studyAt> studyat) {
		//this.studyat = studyat;
	//}
}