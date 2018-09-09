package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person {

	@Id
	private long personID;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	private String firstName;
	private String lastName;
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String browserUsed;
	private String locationIP;

	@ManyToOne
	@JoinColumn(name = "city")
	private City city;
	
	//@OneToMany(mappedBy="person")
	//private List<studyAt> studyat = new ArrayList<studyAt>();
			
	//@OneToMany(mappedBy="person")
	//private List<workAt> workat = new ArrayList<workAt>();

	public Person() {
	}
	
	public Person(long personID, Date creationDate, String firstName, String lastName, String gender,
			Date birthday, String browserUsed, String locationIP, City city) {
		this.personID = personID;
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.city = city;
	}

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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
	
	//public List<studyAt> getStudyat() {
		//return studyat;
	//}

	//public void setStudyat(List<studyAt> studyat) {
		//this.studyat = studyat;
	//}

	//public List<workAt> getWorkat() {
		//return workat;
	//}

	//public void setWorkat(List<workAt> workat) {
		//this.workat = workat;
	//}
}