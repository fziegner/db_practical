package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

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
	
	@ManyToMany(mappedBy = "likes")
	private List<Comments> likedComments;
	
	@ManyToMany(mappedBy = "interestedPersons")
	private List<Tag> interests;
	
	@OneToMany(mappedBy = "person")
	private List<WorkAt> workAt;
	
	@OneToMany(mappedBy="person")
	private List<StudyAt> studyAt;
	
	@ElementCollection
	@CollectionTable(name = "pkp_symmetric", joinColumns = @JoinColumn(name = "personOne"))
	@Column(name = "creationDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@MapKeyJoinColumn(name = "personTwo")
	private Map<Person, Calendar> friends = new HashMap<Person, Calendar>();

	public Person() {
	}

	public Person(long personID, Date creationDate, String firstName, String lastName, String gender, Date birthday,
			String browserUsed, String locationIP, City city, List<Comments> likedComments, List<Tag> interests,
			List<WorkAt> workAt, List<StudyAt> studyAt, Map<Person, Calendar> friends) {
		this.personID = personID;
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.browserUsed = browserUsed;
		this.locationIP = locationIP;
		this.city = city;
		this.likedComments = likedComments;
		this.interests = interests;
		this.workAt = workAt;
		this.studyAt = studyAt;
		this.friends = friends;
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

	public List<Comments> getLikedComments() {
		return likedComments;
	}

	public void setLikedComments(List<Comments> likedComments) {
		this.likedComments = likedComments;
	}

	public List<Tag> getInterests() {
		return interests;
	}

	public void setInterests(List<Tag> interests) {
		this.interests = interests;
	}

	public List<WorkAt> getWorkAt() {
		return workAt;
	}

	public void setWorkAt(List<WorkAt> workAt) {
		this.workAt = workAt;
	}

	public List<StudyAt> getStudyAt() {
		return studyAt;
	}

	public void setStudyAt(List<StudyAt> studyAt) {
		this.studyAt = studyAt;
	}

	public Map<Person, Calendar> getFriends() {
		return friends;
	}

	public void setFriends(Map<Person, Calendar> friends) {
		this.friends = friends;
	}

	public void addFriend(Person friend) {
		if(friends.containsKey(friend) != true) {
			Calendar date = new GregorianCalendar();
			friends.put(friend, date);
		}
	}
	
	public String toString() {
		return Long.toString(personID);
	}
}