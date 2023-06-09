package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person {

	@Id
	@Column(name = "personID")
	private Long personID;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	@Column(nullable = false, length = 64)
	String firstName;
	
	@Column(nullable = false, length = 64)
	String lastName;
	
	@Column(length = 8)
	String gender;
	
	@Column(columnDefinition = "birthday")
	private Calendar birthday;
	
	@Column(length = 64)
	String browserUsed;
	
	@Column(nullable = false, columnDefinition = "inet")
	private String locationIP;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "city", nullable = false)
	private City city;
	
	@OneToMany(mappedBy="person")
	private List<studyAt> studyat = new ArrayList<studyAt>();
	
	@OneToMany(mappedBy="person")
	private List<workAt> workat = new ArrayList<workAt>();
	
	public Person() {
	}

	public Person(Long personID, Calendar creationDate, String firstName, String lastName, String gender,
			Calendar birthday, String browserUsed, String locationIP, City city, List<studyAt> studyat,
			List<workAt> workat) {
		this.personID = personID;
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.city = city;
		this.studyat = studyat;
		this.workat = workat;
	}

	public Long getPersonID() {
		return personID;
	}

	public void setPersonID(Long personID) {
		this.personID = personID;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getBrowserUsed() {
		return browserUsed;
	}

	public void setBrowserUsed(String browserUsed) {
		this.browserUsed = browserUsed;
	}

	public String getLocationIP() {
		return locationIP;
	}

	public void setLocationIP(String locationIP) {
		this.locationIP = locationIP;
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

	public List<workAt> getWorkat() {
		return workat;
	}

	public void setWorkat(List<workAt> workat) {
		this.workat = workat;
	}
}