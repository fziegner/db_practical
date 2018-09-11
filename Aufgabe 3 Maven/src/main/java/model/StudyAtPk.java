package model;

import java.io.Serializable;

public class StudyAtPk implements Serializable {
	
    private Person person;
    private University university;
    
	public StudyAtPk() {
	}

	public StudyAtPk(Person person, University university) {
		this.person = person;
		this.university = university;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return false;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
}