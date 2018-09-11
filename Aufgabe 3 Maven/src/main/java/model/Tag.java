package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@Id
	private int tagID;
	private String tagName;
	
	@ManyToMany
	@JoinTable(name = "has_interest",
			   joinColumns = {@JoinColumn(name = "tag")},
               inverseJoinColumns = {@JoinColumn(name = "personid")})
	private List<Person> interestedPersons;
	
	//TODO Tag Class Type
	
	public Tag() {	
	}

	public Tag(int tagID, String tagName, List<Person> interestedPersons) {
		this.tagID = tagID;
		this.tagName = tagName;
		this.interestedPersons = interestedPersons;
	}

	public int getTagID() {
		return tagID;
	}

	public void setTagID(int tagID) {
		this.tagID = tagID;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Person> getInterestedPersons() {
		return interestedPersons;
	}

	public void setInterestedPersons(List<Person> interestedPersons) {
		this.interestedPersons = interestedPersons;
	}
}